package com.example.harbor_employee.Employee.service;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.dto.NameBirthDto;
import com.example.harbor_employee.Employee.dto.request.EmployeeSearchDto;
import com.example.harbor_employee.Employee.dto.request.EmployeeUpdateRequestDto;
import com.example.harbor_employee.Employee.dto.response.*;
import com.example.harbor_employee.Employee.repository.EmployeeRepository;
import com.example.harbor_employee.client.TotalClient;
import com.example.harbor_employee.client.dto.EmployeeStatusDto;
import com.example.harbor_employee.global.util.EmployeeSpecification;
import com.example.harbor_employee.client.dto.LoginMemberResDto;
import com.example.harbor_employee.global.util.S3UploadUtil;
import com.example.harbor_employee.kafka.dto.KafkaDetailDto;
import com.example.harbor_employee.kafka.TestProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TestProducer testProducer;
    private final TotalClient totalClient;
    private final S3UploadUtil s3UploadUtil;

    public EmployeeService(EmployeeRepository employeeRepository, TestProducer testProducer, TotalClient totalClient, S3UploadUtil s3UploadUtil) {
        this.employeeRepository = employeeRepository;
        this.testProducer = testProducer;
        this.totalClient = totalClient;
        this.s3UploadUtil = s3UploadUtil;
    }

    public List<EmployeeResDto> findAll(EmployeeSearchDto employeeSearchDto, Pageable pageable) {
        Specification<T> specification =
                EmployeeSpecification.likeName(employeeSearchDto.getName())
                        .and(EmployeeSpecification.likeEmployeeId(employeeSearchDto.getEmployeeId()))
                        .and(EmployeeSpecification.equalDepartment(employeeSearchDto.getDepartmentCode()))
                        .and(EmployeeSpecification.equalTeamName(employeeSearchDto.getTeamCode()));

        Page<Employee> employeePage = employeeRepository.findAll(specification, pageable);
        List<Employee> employeeList = employeePage.getContent();
        List<String> employeeIdList = new ArrayList<>();
        for(Employee employee: employeeList){
            employeeIdList.add(employee.getEmployeeId());
        }
        List<EmployeeStatusDto> employeeStatusDtos = totalClient.getStatus(employeeIdList);

        Map<String, EmployeeStatusDto> employeeStatusMap = employeeStatusDtos.stream()
                .collect(Collectors.toMap(EmployeeStatusDto::getEmployeeId, Function.identity()));

        List<EmployeeResDto> employeeResDtos = employeeList.stream()
                .map(e -> {
                    EmployeeStatusDto status = employeeStatusMap.getOrDefault(e.getEmployeeId(), null);
                    return EmployeeResDto.builder()
                            .employeeId(e.getEmployeeId())
                            .department(e.getDepartmentCode())
                            .team(e.getTeamCode())
                            .position(e.getPositionCode())
                            .name(e.getName())
                            .profileImagePath(e.getProfileImage())
                            .email(e.getEmail())
                            .phone(e.getPhone())
                            .status(status != null ? status.getStatusCode() : null)
                            .build();
                }).collect(Collectors.toList());
        return employeeResDtos;
    }

    public HttpStatus createBasicEmployee(LoginMemberResDto loginMemberResDto) {

        log.info(" basicEmployee 객체 생성");
        Employee basicEmployee = Employee.createLogin(
                loginMemberResDto.getEmail(),
                loginMemberResDto.getBirth(),
                loginMemberResDto.getName(),
                loginMemberResDto.getEmployeeId()
        );
        employeeRepository.save(basicEmployee);
        return HttpStatus.OK;
    }

    public EmployeeDetailResDto findByEmployeeId(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 직원을 찾을 수 없습니다."));
        return EmployeeDetailResDto.builder()
                .employeeId(employeeId)
                .name(employee.getName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .gender(employee.getGenderCode())
                .birthDate(employee.getBirthDate())
                .socialSecurityNumber(employee.getSocialSecurityNumber())
                .address(employee.getAddress())
                .duty(employee.getDutyCode())
                .position(employee.getPositionCode())
                .team(employee.getTeamCode())
                .department(employee.getDepartmentCode())
                .status(employee.getStatusCode())
                .careerYMD(employee.getCareerYMD())
                .joinDate(employee.getJoinDate())
                .bank(employee.getBankCode())
                .accountNumber(employee.getAccountNumber())
                .profileImagePath(employee.getProfileImage())
                .leavingDate(employee.getLeavingDate())
                .reasonForResignation(employee.getReasonForResignation())
                .build();
    }

    public GetEmployResponse getUserPosition(String employeeId) {
        try{
            Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(IllegalArgumentException::new);
            System.out.println("employee.getPositionCode() = " + employee.getPositionCode());
            GetEmployResponse getEmployResponse = new GetEmployResponse();
            getEmployResponse.getResults().add(new GetEmployResponse.Result(employee.getPositionCode()));
            System.out.println("getEmployResponse.getResults() = " + getEmployResponse.getResults());
            getEmployResponse.getResults().add(new GetEmployResponse.Result(employee.getPositionCode()));
            return getEmployResponse;
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public EmployeeDetailResDto updateEmployee(EmployeeUpdateRequestDto request, MultipartFile file) throws IOException {
        Employee employee = employeeRepository.findByEmployeeId(request.getEmployeeId()).orElseThrow(() -> new IllegalArgumentException(" 없는 employee 입니다 "));
        String filePath = "";
        employee.updateEmployee(filePath,request.getPhone(),request.getAddress());
        if(file != null) {
            if(!file.getContentType().startsWith("image/"))
                throw new IOException("적절하지 않은 파일 확장자입니다.");
            filePath = s3UploadUtil.upload(file, "profile");
            employee.setImage(filePath);
        }
        return EmployeeDetailResDto.toDto(employee);
    }

    public List<ExcelEmployeeDto> create(MultipartFile file) throws IOException {

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        System.out.println("extension = " + extension);
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IllegalArgumentException("엑셀 파일만 올려주세요.");
        }

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = null;

            if (extension.equals("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (extension.equals("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            }

            List<ExcelEmployeeDto> dataList = new ArrayList<>();
            Sheet worksheet = workbook.getSheetAt(0);

            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                Row row = worksheet.getRow(i);
                if (row != null) {
                    ExcelEmployeeDto excelEmployeeDto = new ExcelEmployeeDto();
                    Cell cell1 = row.getCell(0);
                    if (cell1 != null)
                        excelEmployeeDto.setEmployeeId(cell1.getStringCellValue());
                    Employee employee = employeeRepository.findByEmployeeId(excelEmployeeDto.getEmployeeId()).orElseThrow(() -> new IllegalArgumentException(" 없는 회원 입니다."));

                    Cell cell2 = row.getCell(1);
                    if (cell2 != null)
                        excelEmployeeDto.setTeamCode(cell2.getStringCellValue());

                    Cell cell3 = row.getCell(2);
                    if (cell3 != null)
                        excelEmployeeDto.setPositionCode(cell3.getStringCellValue());

                    Cell cell4 = row.getCell(3);
                    if (cell4 != null)
                        excelEmployeeDto.setDutyCode(cell4.getStringCellValue());

                    Cell cell5 = row.getCell(4);
                    System.out.println("cell5 = " + cell5);
                    if (cell5 != null) {
                        excelEmployeeDto.setStatusCode(cell5.getStringCellValue());
                    }

                    Cell cell6 = row.getCell(5);
                    if (cell6 != null)
                        excelEmployeeDto.setGenderCode(cell6.getStringCellValue());

                    Cell cell7 = row.getCell(6);
                    if (cell7 != null)
                        excelEmployeeDto.setBankCode(cell7.getStringCellValue());

                    Cell cell8 = row.getCell(7);
                    if (cell8 != null)
                        excelEmployeeDto.setSocialSecurityNumber(cell8.getStringCellValue());

                    Cell cell9 = row.getCell(8);
                    if (cell9 != null)
                        excelEmployeeDto.setAddress(cell9.getStringCellValue());

                    Cell cell10 = row.getCell(9);
                    if (cell10 != null)
                        excelEmployeeDto.setCareerYMD(cell10.getStringCellValue());

                    Cell cell11 = row.getCell(10);
                    if (cell11 != null) {
                        if (cell11.getCellType() == CellType.NUMERIC) {
                            Date date = cell11.getDateCellValue();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            excelEmployeeDto.setJoinDate(dateFormat.format(date));
                        } else if (cell11.getCellType() == CellType.STRING) {
                            excelEmployeeDto.setJoinDate(cell11.getStringCellValue());
                        }
                    }
                    Cell cell12 = row.getCell(11);
                    if (cell12 != null)
                        excelEmployeeDto.setAccountNumber(cell12.getStringCellValue());

                    Cell cell13 = row.getCell(12);
                    if (cell13 != null)
                        excelEmployeeDto.setDepartmentCode(cell13.getStringCellValue());

                    Cell cell14 = row.getCell(13);
                    if (cell14 != null)
                        excelEmployeeDto.setPhone(cell14.getStringCellValue());
                    dataList.add(excelEmployeeDto);
                    employee.updateEmployee(excelEmployeeDto);
                    KafkaDetailDto kafkaDetailDto = KafkaDetailDto.builder()
                            .employeeId(employee.getEmployeeId())
                            .teamCode(employee.getTeamCode())
                            .positionCode(employee.getPositionCode())
                            .dutyCode(employee.getDutyCode())
                            .statusCode(employee.getStatusCode())
                            .genderCode(employee.getGenderCode())
                            .bankCode(employee.getBankCode())
                            .socialSecurityNumber(employee.getSocialSecurityNumber())
                            .address(employee.getAddress())
                            .careerYMD(employee.getCareerYMD())
                            .joinDate(employee.getJoinDate())
                            .accountNumber(employee.getAccountNumber())
                            .departmentCode(employee.getDepartmentCode())
                            .phone(employee.getPhone())
                            .name(employee.getName())
                            .email(employee.getEmail())
                            .build();
                    testProducer.sendToKafka("createUser", kafkaDetailDto);
                }
            }
            return dataList;
        } catch (IOException e) {
            // 파일 처리 중 예외 발생 시 예외를 던짐
            throw new IOException("파일을 처리하는 도중 오류가 발생");
        }
    }

    public NameBirthDto getObject(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        return NameBirthDto.builder()
                .birth(employee.getBirthDate())
                .name(employee.getName())
                .build();
    }

    public List<EmployeeStatusDto> getemployeeStautsList(List<String> employeeIdList){
        return totalClient.getStatus(employeeIdList);
    }
}

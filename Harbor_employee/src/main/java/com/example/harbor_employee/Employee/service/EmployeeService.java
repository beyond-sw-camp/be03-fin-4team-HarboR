package com.example.harbor_employee.Employee.service;

import com.example.harbor_employee.global.support.Code;
import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.dto.request.EmployeeSearchDto;
import com.example.harbor_employee.Employee.dto.request.EmployeeUpdateRequestDto;
import com.example.harbor_employee.Employee.dto.response.EmployeeDetailResDto;
import com.example.harbor_employee.Employee.dto.response.EmployeeResDto;
import com.example.harbor_employee.Employee.dto.response.GetEmployResponse;
import com.example.harbor_employee.Employee.repository.EmployeeRepository;
import com.example.harbor_employee.global.util.EmployeeSpecification;
import com.example.harbor_employee.client.dto.LoginMemberResDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResDto> findAll(EmployeeSearchDto employeeSearchDto, Pageable pageable) {
        Specification<T> specification =
                EmployeeSpecification.likeName(employeeSearchDto.getName())
                        .and(EmployeeSpecification.likeEmployeeId(employeeSearchDto.getEmployeeId()))
                        .and(EmployeeSpecification.equalDepartment(employeeSearchDto.getDepartmentCode()))
                        .and(EmployeeSpecification.equalTeamName(employeeSearchDto.getTeamCode()));

        Page<Employee> employeePage = employeeRepository.findAll(specification, pageable);
        List<Employee> employeeList = employeePage.getContent();
        List<EmployeeResDto> employeeResDtos = new ArrayList<>();
        employeeResDtos = employeeList.stream()
                .map(e-> EmployeeResDto.builder()
                        .employeeId(e.getEmployeeId())
                        .department(Code.valueOf(e.getDepartmentCode()).getMessage())
                        .team(Code.valueOf(e.getTeamCode()).getMessage())
                        .position(Code.valueOf(e.getPositionCode()).getMessage())
                        .name(e.getName())
                        .build()).collect(Collectors.toList());
        return employeeResDtos;
    }

    public HttpStatus createBasicEmployee(LoginMemberResDto loginMemberResDto) {

        log.info(" basicEmployee 객체 생성");
        System.out.println("loginMemberResDto = " + loginMemberResDto.getBirth());
        System.out.println("loginMemberResDto = " + loginMemberResDto.getName());
        System.out.println("loginMemberResDto = " + loginMemberResDto.getEmail());
        System.out.println("loginMemberResDto = " + loginMemberResDto.getEmployeeId());
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
                .gender(Code.valueOf(employee.getGenderCode()).getMessage())
                .birthDate(employee.getBirthDate())
                .socialSecurityNumber(employee.getSocialSecurityNumber())
                .address(employee.getAddress())
                .duty(Code.valueOf(employee.getDutyCode()).getMessage())
                .position(Code.valueOf(employee.getPositionCode()).getMessage())
                .team(Code.valueOf(employee.getTeamCode()).getMessage())
                .department(Code.valueOf(employee.getDepartmentCode()).getMessage())
                .status(Code.valueOf(employee.getStatusCode()).getMessage())
                .careerYMD(employee.getCareerYMD())
                .joinDate(employee.getJoinDate())
                .bank(Code.valueOf(employee.getBankCode()).getMessage())
                .accountNumber(employee.getAccountNumber())
                .profileImagePath(employee.getProfileImage())
                .leavingDate(employee.getLeavingDate())
                .reasonForResignation(employee.getReasonForResignation())
                .build();
    }

    public GetEmployResponse getUserPosition(String employeeId) {
        try{
            Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(IllegalArgumentException::new);
            GetEmployResponse getEmployResponse = new GetEmployResponse();
//            getEmployResponse.setPositionCode(employee.getPositionCode().getDescription());
            return getEmployResponse;
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public EmployeeDetailResDto updateEmployee(EmployeeUpdateRequestDto request, String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() -> new IllegalArgumentException(" 없는 employee 입니다 "));

        MultipartFile multipartFile = request.getProfileImage();
        String fileName = multipartFile.getOriginalFilename();

        Path path = Paths.get("/Users/song/Desktop/코딩공부/tmp", employee.getEmployeeId() + "_" + fileName);
        employee.setImage(path.toString());
        employee.updateEmployee(path.toString(),request.getPhone());
        try {
            byte[] bytes = multipartFile.getBytes();
            Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE); // 없으면 넣고 있으면 덮어쓰기
        } catch (IOException e) {
            throw new IllegalArgumentException("image not available");
        }

        return EmployeeDetailResDto.toDto(employee);
    }
}

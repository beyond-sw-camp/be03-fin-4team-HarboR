package com.example.harbor_employee.Employee.service;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.dto.request.EmployeeSearchDto;
import com.example.harbor_employee.Employee.dto.response.EmployeeDetailResDto;
import com.example.harbor_employee.Employee.dto.response.EmployeeResDto;
import com.example.harbor_employee.Employee.dto.response.GetEmployResponse;
import com.example.harbor_employee.Employee.repository.EmployeeRepository;
import com.example.harbor_employee.global.util.EmployeeSpecification;
import com.example.harbor_employee.client.dto.LoginMemberResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
        log.info("DepartmentName: " + employeeSearchDto.getDepartmentName());
        Specification<Employee> specification =
                EmployeeSpecification.likeName(employeeSearchDto.getName())
                        .and(EmployeeSpecification.likeEmployeeId(employeeSearchDto.getEmployeeId()))
                        .and(EmployeeSpecification.equalDepartment(employeeSearchDto.getDepartmentName()))
                        .and(EmployeeSpecification.equalTeamName(employeeSearchDto.getTeamName()));

        List<Employee> employeeList = employeeRepository.findAll(specification, pageable);
        List<EmployeeResDto> employeeResDtos = new ArrayList<>();
        employeeResDtos = employeeList.stream()
                .map(e-> EmployeeResDto.builder()
                        .employeeId(e.getEmployeeId())
                        .department(e.getDepartment().getDepartmentName())
                        .team(e.getTeamCode().getDescription())
                        .position(e.getPositionCode().getDescription())
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
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(IllegalArgumentException::new);
        return EmployeeDetailResDto.builder()
                .employeeId(employeeId)
                .name(employee.getName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .gender(employee.getGenderCode().getDescription())
                .birthDate(employee.getBirthDate())
                .socialSecurityNumber(employee.getSocialSecurityNumber())
                .address(employee.getAddress())
                .duty(employee.getDutyCode().getDescription())
                .position(employee.getPositionCode().getDescription())
                .team(employee.getTeamCode().getDescription())
                .department(employee.getDepartment().getDepartmentName())
                .status(employee.getStatusCode().getDescription())
                .careerYMD(employee.getCareerYMD())
                .joinDate(employee.getJoinDate())
                .bank(employee.getBankCode().getDescription())
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
            System.out.println("getEmployResponse.getResults(). = " + getEmployResponse.getResults().);
            return getEmployResponse;
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}

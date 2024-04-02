package com.example.harbor_employee.Employee.service;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.domain.EmployeeCode;
import com.example.harbor_employee.Employee.dto.request.EmployeeSearchDto;
import com.example.harbor_employee.Employee.dto.response.EmployeeResDto;
import com.example.harbor_employee.Employee.dto.response.GetEmployResponse;
import com.example.harbor_employee.Employee.repository.EmployeeCodeRepository;
import com.example.harbor_employee.Employee.repository.EmployeeRepository;
import com.example.harbor_employee.client.dto.LoginMemberResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeCodeRepository employeeCodeRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeCodeRepository employeeCodeRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCodeRepository = employeeCodeRepository;
    }

    public List<EmployeeResDto> findAll(EmployeeSearchDto employeeSearchDto, Pageable pageable) {
        Specification<Employee> spec = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(employeeSearchDto.getName() != null){
                    predicates.add(criteriaBuilder
                            .like(root.get("name"), "%" + employeeSearchDto.getName() + "%"));
                }
                if(employeeSearchDto.getEmployeeId() != null){
                    predicates.add(criteriaBuilder
                            .like(root.get("employeeId"), "%" + employeeSearchDto.getEmployeeId() + "%"));
                }
                if(employeeSearchDto.getDepartmentName() != null){
                    EmployeeCode employeeCode = employeeCodeRepository.findByDescription(employeeSearchDto.getDepartmentName())
                            .orElseThrow(() -> new IllegalArgumentException("해당 부서는 없는 부서입니다."));
                    predicates.add(criteriaBuilder
                            .like(root.get("department"), "%" + employeeCode.getCode() + "%"));
                }
                if(employeeSearchDto.getTeamName() != null){
                    EmployeeCode employeeCode = employeeCodeRepository.findByDescription(employeeSearchDto.getDepartmentName())
                            .orElseThrow(() -> new IllegalArgumentException("해당 팀 이름은 없는 팀 이름입니다."));
                    predicates.add(criteriaBuilder
                            .like(root.get("team"), "%" + employeeCode.getCode() + "%"));
                }
                Predicate[] predicatesArr = new Predicate[predicates.size()];
                for(int i=0; i<predicates.size(); i++){
                    predicatesArr[i] = predicates.get(i);
                }
                Predicate predicate = criteriaBuilder.and(predicatesArr);
                return predicate;
            }
        };
        Page<Employee> employees = employeeRepository.findAll(spec, pageable);
        List<Employee> employeeList = employees.getContent();
        List<EmployeeResDto> employeeResDtos = new ArrayList<>();
        employeeResDtos = employeeList.stream()
                .map(e-> EmployeeResDto.builder()
                        .employeeId(e.getEmployeeId())
                        .department(employeeCodeRepository.findByCode(e.getDepartment().getDepartmentCode()).orElseThrow(IllegalArgumentException::new).getDescription())
                        .team(employeeCodeRepository.findByCode(e.getDepartment().getDepartmentCode()).orElseThrow(IllegalArgumentException::new).getDescription())
                        .position(employeeCodeRepository.findByCode(e.getDepartment().getDepartmentCode()).orElseThrow(IllegalArgumentException::new).getDescription())
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

    public GetEmployResponse getUserPosition(String employeeId) {
        try{
            Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(IllegalArgumentException::new);
            GetEmployResponse getEmployResponse = new GetEmployResponse();
            getEmployResponse.getResults().add(new GetEmployResponse.Result(employee.getPositionCode()));
            log.info(getEmployResponse.getResults().get(0).getPositionCode());
            return getEmployResponse;
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}

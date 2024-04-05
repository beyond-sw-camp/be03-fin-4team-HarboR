package com.example.harbor_employee.Eworks.service;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.repository.EmployeeRepository;
import com.example.harbor_employee.Eworks.domain.Eworks;
import com.example.harbor_employee.Eworks.dto.response.EworksAuthList;
import com.example.harbor_employee.Eworks.dto.request.EworksCreateReqDto;
import com.example.harbor_employee.Eworks.dto.response.EworksListResDto;
import com.example.harbor_employee.Eworks.repository.EworksRepository;
import com.example.harbor_employee.global.support.Code;
import com.example.harbor_employee.global.support.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class EworksService {
    private final EworksRepository eworksRepository;
    private final EmployeeRepository employeeRepository;

    public EworksService(EworksRepository eworksRepository, EmployeeRepository employeeRepository) {
        this.eworksRepository = eworksRepository;
        this.employeeRepository = employeeRepository;
    }

    public void create(String employeeId, EworksCreateReqDto eworksCreateReqDto){
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다"));
        Eworks eworks = Eworks.createEworks(
                employee,
                eworksCreateReqDto.getPayStatusCode(),
                eworksCreateReqDto.getFirstSignId(),
                eworksCreateReqDto.getSecondSignId(),
                eworksCreateReqDto.getThirdSignId());
        eworksRepository.save(eworks);
    }

    public List<EworksListResDto> getSendList(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다"));
        List<Eworks> eworksList = eworksRepository.findAllByEmployee(employee);
        List<EworksListResDto> eworksListResDtos = new ArrayList<>();
        for(Eworks eworks : eworksList){
            EworksListResDto eworksListResDto = EworksListResDto.create(
                            Code.valueOf(eworks.getPayStatusCode()).getMessage(),
                            eworks.getFirstSignId(),
                            eworks.getFirstApprovalDate(),
                            eworks.getSecondSignId(),
                            eworks.getSecondApprovalDate(),
                            eworks.getThirdSignId(),
                            eworks.getThirdApprovalDate());
            eworksListResDtos.add(eworksListResDto);
        }
        return eworksListResDtos;
    }

    public List<EworksListResDto> getReceiveList(String employeeId) {
        List<Eworks> eworksList = eworksRepository.findAllByFirstSignIdOrSecondSignIdOrThirdSignId(employeeId);
        List<EworksListResDto> eworksListResDtos = new ArrayList<>();
        for(Eworks eworks : eworksList){
            EworksListResDto eworksListResDto = EworksListResDto.builder()
                    .payStatusName(Code.valueOf(eworks.getPayStatusCode()).getMessage())
                    .firstApprovalId(eworks.getFirstSignId())
                    .firstApprovalDate(eworks.getFirstApprovalDate())
                    .secondApprovalId(eworks.getSecondSignId())
                    .secondApprovalDate(eworks.getSecondApprovalDate())
                    .thirdApprovalId(eworks.getThirdSignId())
                    .thirdApprovalDate(eworks.getThirdApprovalDate())
                    .build();
            eworksListResDtos.add(eworksListResDto);
        }
        return eworksListResDtos;
    }

    //ToDo: 승인권자 리스트 뽑을 때 로직이나 조건을 좀 더 생각해봐야겠음
    public EworksAuthList getAuthList(String departmentCode) {
        String firstAuthCode = Department.valueOf(departmentCode).getCode();
        String secondAuthCode = Department.valueOf(firstAuthCode).getCode();
        String thirdAuthCode = Department.valueOf(secondAuthCode).getCode();
        List<Employee> firstAuthList = employeeRepository.findAllByDepartmentCode(firstAuthCode);
        List<Employee> secondAuthList = employeeRepository.findAllByDepartmentCode(secondAuthCode);
        List<Employee> thirdAuthList = employeeRepository.findAllByDepartmentCode(thirdAuthCode);

        return null;
    }

    public Object updateApproval(String payId) {
    }
}

package com.example.harbor_employee.Eworks.service;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.repository.EmployeeRepository;
import com.example.harbor_employee.Eworks.controller.GetAuthListReqDto;
import com.example.harbor_employee.Eworks.domain.Eworks;
import com.example.harbor_employee.Eworks.dto.response.EworksAuthList;
import com.example.harbor_employee.Eworks.dto.request.EworksCreateReqDto;
import com.example.harbor_employee.Eworks.dto.response.EworksListResDto;
import com.example.harbor_employee.Eworks.repository.EworksRepository;
import com.example.harbor_employee.global.support.Code;
import com.example.harbor_employee.global.support.Department;
import com.example.harbor_employee.global.support.Position;
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

    public EworksAuthList getAuthList(GetAuthListReqDto getAuthListReqDto) {
        Employee employee = employeeRepository.findByEmployeeId(getAuthListReqDto.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        String firstAuthCode = getAuthListReqDto.getDepartmentCode();
        String secondAuthCode = Department.valueOf(firstAuthCode).getCode();
        String thirdAuthCode = Department.valueOf(secondAuthCode).getCode();
        // 내가 해당하는 포지션 코드 획득
        List<EworksAuthList.Inform> firstInforms = new ArrayList<>();
        List<EworksAuthList.Inform> secondInforms = new ArrayList<>();
        List<EworksAuthList.Inform> thirdInforms = new ArrayList<>();
        EworksAuthList eworksAuthList = new EworksAuthList();
        String firstPositionCode = Position.getPositionByCode(employee.getPositionCode()).getHighPosition();
        List<Employee> firstAuthList = employeeRepository.findEmployeesByDepartmentCodeAndPositionCodeRange(
                firstPositionCode,
                Position.valueOf(firstPositionCode).getHighPosition(),
                firstAuthCode);
        for (Employee value : firstAuthList) {
            System.out.println("저,, 나왔어요,,," + value.getName());
            firstInforms.add(EworksAuthList.Inform.create(value.getName(), value.getEmployeeId()));
        }
        eworksAuthList.setFirstAuthorizer(firstInforms);
        System.out.println(eworksAuthList.getFirstAuthorizer().get(0));
        if(!firstAuthCode.startsWith("K") && !String.valueOf(firstPositionCode).startsWith("S6")){
            String secondPositionCode = Position.getPositionByCode(String.valueOf(firstPositionCode)).getHighPosition();
            List<Employee> secondAuthList = employeeRepository.findEmployeesByDepartmentCodeAndPositionCodeRange(
                    secondPositionCode,
                    Position.valueOf(secondPositionCode).getHighPosition(),
                    secondAuthCode);
            for (Employee value : secondAuthList) {
                secondInforms.add(EworksAuthList.Inform.create(value.getName(), value.getEmployeeId()));
            }
            eworksAuthList.setSecondAuthorizer(secondInforms);
            if(secondAuthCode.startsWith("K") && String.valueOf(firstPositionCode).startsWith("S6"))
                return eworksAuthList;

            String thirdPositionCode = Position.getPositionByCode(String.valueOf(secondPositionCode)).getHighPosition();
            List<Employee> thirdAuthList = employeeRepository.findEmployeesByDepartmentCodeAndPositionCodeRange(
                    thirdPositionCode,
                    Position.valueOf(thirdPositionCode).getHighPosition(),
                    thirdAuthCode);
            for (Employee value : thirdAuthList) {
                thirdInforms.add(EworksAuthList.Inform.create(value.getName(), value.getEmployeeId()));
            }
            eworksAuthList.setThirdAuthorizer(thirdInforms);
        }
        return eworksAuthList;
    }

    public Object updateApproval(String payId) {
        return null;
    }
}

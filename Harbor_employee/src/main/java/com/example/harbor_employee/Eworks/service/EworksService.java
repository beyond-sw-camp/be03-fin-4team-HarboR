package com.example.harbor_employee.Eworks.service;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.Employee.repository.EmployeeRepository;
import com.example.harbor_employee.Eworks.controller.GetAuthListReqDto;
import com.example.harbor_employee.Eworks.domain.Eworks;
import com.example.harbor_employee.Eworks.dto.request.ApprovalReqDto;
import com.example.harbor_employee.Eworks.dto.response.EworksAuthList;
import com.example.harbor_employee.Eworks.dto.request.EworksCreateReqDto;
import com.example.harbor_employee.Eworks.dto.response.EworksListResDto;
import com.example.harbor_employee.Eworks.dto.response.EworksReceiveListResDto;
import com.example.harbor_employee.Eworks.repository.EworksRepository;
import com.example.harbor_employee.global.support.Approval;
import com.example.harbor_employee.global.support.Code;
import com.example.harbor_employee.global.support.Department;
import com.example.harbor_employee.global.support.Position;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.BadAttributeValueExpException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@Transactional
public class EworksService {
    private final EworksRepository eworksRepository;
    private final EmployeeRepository employeeRepository;

    private final EworksAuthList eworksAuthList = new EworksAuthList();

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
                            eworks.getPayStatusCode(),
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

    //Todo: 결재 요청 상세 내용은 어떻게 표시하는게 좋을까?
    public List<EworksReceiveListResDto> getReceiveList(String employeeId) {
        List<Eworks> eworksList = eworksRepository.findAllByFirstSignIdOrSecondSignIdOrThirdSignId(employeeId);
        List<EworksReceiveListResDto> eworksListResDtos = new ArrayList<>();
        for(Eworks eworks : eworksList){
            EworksReceiveListResDto eworksListResDto = EworksReceiveListResDto.create(
                    eworks.getPayId(),
                    employeeId,
                    eworks.getPayStatusCode());
            eworksListResDtos.add(eworksListResDto);
        }
        return eworksListResDtos;
    }

    public EworksAuthList getAuthList(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        String firstAuthCode = employee.getDepartmentCode();
        String secondAuthCode = Department.valueOf(firstAuthCode).getCode();
        String thirdAuthCode = Department.valueOf(secondAuthCode).getCode();
        List<EworksAuthList.Inform> firstInforms = new ArrayList<>();
        List<EworksAuthList.Inform> secondInforms = new ArrayList<>();
        List<EworksAuthList.Inform> thirdInforms = new ArrayList<>();

        String firstPositionCode = getPositionCode(employee.getPositionCode());
        List<Employee> firstAuthList = employeeRepository.findEmployeesByDepartmentCodeAndPositionCodeRange(
                firstPositionCode,
                Position.valueOf(firstPositionCode).getHighPosition(),
                firstAuthCode);
        for (Employee value : firstAuthList) {
            firstInforms.add(EworksAuthList.Inform.create(value.getName(), value.getEmployeeId()));
        }
        eworksAuthList.setFirstAuthorizer(firstInforms);

        if(!firstAuthCode.startsWith("K") && !String.valueOf(firstPositionCode).startsWith("S6")){
            String secondPositionCode = getPositionCode(firstPositionCode);
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

            String thirdPositionCode = getPositionCode(secondPositionCode);
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

    public String getPositionCode(String code){
        return Position.getPositionByCode(String.valueOf(code)).getHighPosition();
    }

    public void updateApproval(ApprovalReqDto approvalReqDto) {
        Eworks eworks = eworksRepository.findById(approvalReqDto.getPayId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 결재입니다"));
        if(!eworks.getFirstSignId().equals(approvalReqDto.getEmployeeId()) && !eworks.getSecondSignId().equals(approvalReqDto.getEmployeeId()) && !eworks.getThirdSignId().equals(approvalReqDto.getEmployeeId()))
            throw new IllegalArgumentException("해당 결제에 대한 승인 권한이 없습니다.");

        if(approvalReqDto.getEmployeeId().equals(eworks.getFirstSignId())){
            if(approvalReqDto.getApprovalStatus().equals(Boolean.TRUE))
                eworks.updateApprovalDate(Approval.FIRST);
            else eworks.updateCompanion(Approval.FIRST);
        }
        if(approvalReqDto.getEmployeeId().equals(eworks.getSecondSignId())){
            if(eworks.getFirstApprovalDate() == null) {
                if(approvalReqDto.getForce().equals(Boolean.TRUE)) {
                    eworks.updateApprovalDate(Approval.SECOND);
                } else throw new IllegalArgumentException("1차 승인권자의 승인이 필요합니다.");
            } else if(eworks.getFirstApprovalDate().startsWith("companion"))
                throw new IllegalArgumentException("반려 처리된 결재입니다.");
            if(approvalReqDto.getApprovalStatus().equals(Boolean.TRUE))
                eworks.updateApprovalDate(Approval.SECOND);
            else eworks.updateCompanion(Approval.SECOND);
        }
        if(approvalReqDto.getEmployeeId().equals(eworks.getThirdSignId())){
            if(eworks.getFirstApprovalDate() == null || eworks.getSecondApprovalDate() == null) {
                if(approvalReqDto.getForce().equals(Boolean.TRUE))
                    eworks.updateApprovalDate(Approval.THIRD);
            } else if(eworks.getSecondApprovalDate().startsWith("companion")) {
                throw new IllegalArgumentException("반려 처리된 결재입니다.");
            } else throw new IllegalArgumentException("앞선 승인권자의 승인이 필요합니다.");
            if(approvalReqDto.getApprovalStatus().equals(Boolean.TRUE))
                eworks.updateApprovalDate(Approval.THIRD);
            else eworks.updateCompanion(Approval.THIRD);
        }
    }
}

package com.example.harbor_total.Annual.service;

import com.example.harbor_total.Annual.domain.Annual;
import com.example.harbor_total.Annual.dto.request.AnnualCreateReqDto;
import com.example.harbor_total.Annual.dto.request.ApprovalReqDto;
import com.example.harbor_total.Annual.dto.response.AnnualListResDto;
import com.example.harbor_total.Annual.dto.response.AuthListResDto;
import com.example.harbor_total.Annual.dto.response.AnnualReceiveListResDto;
import com.example.harbor_total.Annual.repository.AnnualRepository;
import com.example.harbor_total.Attendance.domain.Attendance;
import com.example.harbor_total.Attendance.repository.AttendanceRepository;
import com.example.harbor_total.Employee.domain.Employee;
import com.example.harbor_total.Employee.repository.EmployeeRepository;
import com.example.harbor_total.global.support.Approval;
import com.example.harbor_total.global.support.Department;
import com.example.harbor_total.global.support.Position;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class AnnualService {
    private final AnnualRepository annualRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public AnnualService(AnnualRepository annualRepository, EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
        this.annualRepository = annualRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

//    public void create(AnnualCreateReqDto annualCreateReqDto){
//        Attendance attendance = attendanceRepository.findById(annualCreateReqDto.getAttendenceId())
//                .orElseThrow(() -> new IllegalArgumentException("해당하는 근무가 없습니다."));
//        Annual annual = Annual.create(
//                annualCreateReqDto.getAttendenceId(),
//                annualCreateReqDto.getAnnualCount(),
//                annualCreateReqDto.getAdjustmentStartDate(),
//                annualCreateReqDto.getAdjustmentEndDate(),
//                annualCreateReqDto.getAdjustmentComment(),
//                annualCreateReqDto.getFirstSignId(),
//                annualCreateReqDto.getSecondSignId(),
//                annualCreateReqDto.getThirdSignId()
//                );
//        annualRepository.save(annual);
//    }

    public List<AnnualListResDto> getSendList(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 회원입니다"));

        List<Attendance> attendanceList = attendanceRepository.findAllByEmployee(employee);
        List<AnnualListResDto> annualListResDtoList = new ArrayList<>();
        for(Attendance attendence : attendanceList){
            AnnualListResDto annualListResDto = AnnualListResDto.create(
                    attendence.getWorkPolicy(),
                    attendence.getAnnuals().getFirstSignId(),
                    attendence.getAnnuals().getFirstApprovalDate(),
                    attendence.getAnnuals().getSecondSignId(),
                    attendence.getAnnuals().getSecondApprovalDate(),
                    attendence.getAnnuals().getThirdSignId(),
                    attendence.getAnnuals().getThirdApprovalDate()
            );
            annualListResDtoList.add(annualListResDto);
        }
        return annualListResDtoList;
    }

    //Todo: 결재 요청 상세 내용은 어떻게 표시하는게 좋을까?
    public List<AnnualReceiveListResDto> getReceiveList(String employeeId) {
        List<Annual> annualList = annualRepository.findAllByFirstSignIdOrSecondSignIdOrThirdSignId(employeeId);
        List<AnnualReceiveListResDto> eworksListResDtos = new ArrayList<>();
        for(Annual annual : annualList){
            Attendance attendance = attendanceRepository.findById(annual.getAnnualId())
                    .orElseThrow(() -> new IllegalArgumentException("근태 정보를 조회할 수 없습니다."));
            AnnualReceiveListResDto eworksListResDto = AnnualReceiveListResDto.create(
                    annual.getAnnualId(),
                    employeeId,
                    attendance.getWorkPolicy());
            eworksListResDtos.add(eworksListResDto);
        }
        return eworksListResDtos;
    }

    public AuthListResDto getAuthList(String employeeId) {
        AuthListResDto authListResDto = new AuthListResDto();
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        String firstAuthCode = employee.getTeamCode();
        String firstPositionCode = getPositionCode(employee.getPositionCode());
        List<AuthListResDto.Inform> firstInforms = new ArrayList<>();
        List<Employee> firstAuthList = employeeRepository.findEmployeesByTeamCodeAndPositionCodeRange(
                firstPositionCode,
                Position.valueOf(firstPositionCode).getHighPosition(),
                firstAuthCode);
        for (Employee value : firstAuthList) {
            firstInforms.add(AuthListResDto.Inform.create(value.getName(), value.getEmployeeId()));
        }
        authListResDto.setFirstAuthorizer(firstInforms);

        if(!firstAuthCode.startsWith("K") && !String.valueOf(firstPositionCode).startsWith("S6")){
            String secondAuthCode = Department.valueOf(firstAuthCode).getCode();
            String secondPositionCode = getPositionCode(firstPositionCode);
            List<AuthListResDto.Inform> secondInforms = new ArrayList<>();
            List<Employee> secondAuthList = employeeRepository.findEmployeesByDepartmentCodeAndPositionCodeRange(
                    secondPositionCode,
                    Position.valueOf(secondPositionCode).getHighPosition(),
                    secondAuthCode);
            for (Employee value : secondAuthList) {
                secondInforms.add(AuthListResDto.Inform.create(value.getName(), value.getEmployeeId()));
            }
            authListResDto.setSecondAuthorizer(secondInforms);

            if(secondAuthCode.startsWith("K") && String.valueOf(firstPositionCode).startsWith("S6"))
                return authListResDto;

            String thirdAuthCode = Department.valueOf(secondAuthCode).getCode();
            String thirdPositionCode = getPositionCode(secondPositionCode);
            List<AuthListResDto.Inform> thirdInforms = new ArrayList<>();
            List<Employee> thirdAuthList = employeeRepository.findEmployeesByDepartmentCodeAndPositionCodeRange(
                    thirdPositionCode,
                    Position.valueOf(thirdPositionCode).getHighPosition(),
                    thirdAuthCode);
            for (Employee value : thirdAuthList) {
                thirdInforms.add(AuthListResDto.Inform.create(value.getName(), value.getEmployeeId()));
            }
            authListResDto.setThirdAuthorizer(thirdInforms);
        }
        return authListResDto;
    }

    public String getPositionCode(String code){
        return Position.getPositionByCode(String.valueOf(code)).getHighPosition();
    }

    public void updateApproval(ApprovalReqDto approvalReqDto) {
        Annual annual = annualRepository.findById(approvalReqDto.getAnnualId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 결재입니다"));
        if(!annual.getFirstSignId().equals(approvalReqDto.getEmployeeId()) && !annual.getSecondSignId().equals(approvalReqDto.getEmployeeId()) && !annual.getThirdSignId().equals(approvalReqDto.getEmployeeId()))
            throw new IllegalArgumentException("해당 결제에 대한 승인 권한이 없습니다.");

        if(approvalReqDto.getEmployeeId().equals(annual.getFirstSignId())){
            if(approvalReqDto.getApprovalStatus().equals(Boolean.TRUE))
                annual.updateApprovalDate(Approval.FIRST);
            else annual.updateCompanion(Approval.FIRST);
        }
        if(approvalReqDto.getEmployeeId().equals(annual.getSecondSignId())){
            if(annual.getFirstApprovalDate() == null) {
                if(approvalReqDto.getForce().equals(Boolean.TRUE))
                    if(approvalReqDto.getApprovalStatus().equals(Boolean.TRUE))
                        annual.updateApprovalDate(Approval.SECOND);
                    else annual.updateCompanion(Approval.SECOND);
                else throw new IllegalArgumentException("앞선 승인권자의 승인이 필요합니다.");
            } else if(annual.getFirstApprovalDate().startsWith("companion")) {
                throw new IllegalArgumentException("반려 처리된 결재입니다.");
            } else {
                if(approvalReqDto.getApprovalStatus().equals(Boolean.TRUE))
                    annual.updateApprovalDate(Approval.SECOND);
                else annual.updateCompanion(Approval.SECOND);
            }
        }
        if(approvalReqDto.getEmployeeId().equals(annual.getThirdSignId())){
            if(annual.getFirstApprovalDate() == null || annual.getSecondApprovalDate() == null) {
                if(approvalReqDto.getForce().equals(Boolean.TRUE))
                    if(approvalReqDto.getApprovalStatus().equals(Boolean.TRUE))
                        annual.updateApprovalDate(Approval.THIRD);
                    else annual.updateCompanion(Approval.THIRD);
                else throw new IllegalArgumentException("앞선 승인권자의 승인이 필요합니다.");
            } else if(annual.getSecondApprovalDate().startsWith("companion")) {
                throw new IllegalArgumentException("반려 처리된 결재입니다.");
            } else {
                if(approvalReqDto.getApprovalStatus().equals(Boolean.TRUE))
                    annual.updateApprovalDate(Approval.THIRD);
                else annual.updateCompanion(Approval.THIRD);
            }
        }
    }
}

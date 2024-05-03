package com.example.harbor_total.Annual.service;

import com.example.harbor_total.Annual.domain.Annual;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AnnualService {
    private final AnnualRepository annualRepository;
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public AnnualService(AnnualRepository annualRepository, EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
        this.annualRepository = annualRepository;
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<AnnualListResDto> getSendList(String employeeId) {
        List<Attendance> attendanceList = attendanceRepository.findAllByEmployee_EmployeeId(employeeId);
        List<AnnualListResDto> annualListResDtoList = new ArrayList<>();
        for(Attendance attendence : attendanceList){
            AnnualListResDto annualListResDto = AnnualListResDto.create(
                attendence.getWorkPolicy(),
                attendence.getAnnuals().getFirstSignId(),
                attendence.getAnnuals().getFirstApprovalDate(),
                attendence.getAnnuals().getSecondSignId(),
                attendence.getAnnuals().getSecondApprovalDate(),
                attendence.getAnnuals().getThirdSignId(),
                attendence.getAnnuals().getThirdApprovalDate());
            annualListResDtoList.add(annualListResDto);
        }
        return annualListResDtoList;
    }

    //Todo: 결재 요청 상세 내용은 어떻게 표시하는게 좋을까?
    public List<AnnualReceiveListResDto> getReceiveList(String employeeId) {
        List<Annual> annualList = annualRepository.findAllByFirstSignIdOrSecondSignIdOrThirdSignId(employeeId);
        List<AnnualReceiveListResDto> eworksListResDtos = new ArrayList<>();
        for(Annual annual : annualList){
            if(checkApproval(annual, employeeId) != null){
                Attendance attendance = attendanceRepository.findById(annual.getAnnualId())
                        .orElseThrow(() -> new IllegalArgumentException("근태 정보를 조회할 수 없습니다."));
                AnnualReceiveListResDto eworksListResDto = AnnualReceiveListResDto.create(
                        annual.getAnnualId(),
                        employeeId,
                        attendance.getWorkPolicy());
                eworksListResDtos.add(eworksListResDto);
            }
        }
        return eworksListResDtos;
    }

    //Todo: 휴결재 처리
    private Annual checkApproval(Annual annual, String employeeId) {
        LocalDateTime startDate = LocalDate.now().atStartOfDay();
        LocalDateTime endDate = LocalDate.now().atTime(23, 59, 59);

        if(annual.getFirstSignId().equals(employeeId) && annual.getFirstApprovalDate() == null){
            return annual;
        } else if(annual.getSecondApprovalDate() != null && annual.getSecondSignId().equals(employeeId)){
            // 1차 승인권자가 휴가인지 체크 ( 리스트가 비어있다면 휴가 처리 )
            List<Attendance> attendanceList = attendanceRepository.findAllByWorkStartTimeBetweenAndEmployeeEmployeeIdOrderByWorkStartTimeDesc(startDate, endDate, annual.getFirstSignId());
            if(annual.getFirstApprovalDate() != null || !attendanceList.isEmpty()){
                if(attendanceList.get(0).getWorkPolicy().equals("E07") && annual.getSecondApprovalDate() == null)
                    return annual;
            }
        } else if(annual.getThirdApprovalDate() != null && annual.getThirdSignId().equals(employeeId)){
            List<Attendance> attendanceList = attendanceRepository.findAllByWorkStartTimeBetweenAndEmployeeEmployeeIdOrderByWorkStartTimeDesc(startDate, endDate, annual.getSecondSignId());
            if(annual.getSecondApprovalDate() != null || !attendanceList.isEmpty()){
                if(attendanceList.get(0).getWorkPolicy().equals("E07") && annual.getThirdApprovalDate() == null)
                    return annual;
            }
        }
        return null;
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

    public void updateApproval(String employeeId, ApprovalReqDto approvalReqDto) {
        Annual annual = annualRepository.findById(approvalReqDto.getAnnualId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 결재입니다"));
        if(!annual.getFirstSignId().equals(employeeId) && !annual.getSecondSignId().equals(employeeId) && !annual.getThirdSignId().equals(employeeId))
            throw new IllegalArgumentException("해당 결제에 대한 승인 권한이 없습니다.");

        if(employeeId.equals(annual.getFirstSignId())){
            if(approvalReqDto.getApprovalStatus().equals(Boolean.TRUE))
                annual.updateApprovalDate(Approval.FIRST);
            else annual.updateCompanion(Approval.FIRST);
        }
        if(employeeId.equals(annual.getSecondSignId())){
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
        if(employeeId.equals(annual.getThirdSignId())){
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

    public HttpStatus deleteAnnual(Long annualId) {
        Attendance attendance = attendanceRepository.findByAttendanceId(annualId)
                .orElseThrow(() -> new IllegalArgumentException("결재 정보를 조회할 수 없습니다."));
        try{
            attendanceRepository.delete(attendance);
        } catch(Exception e){
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }
}

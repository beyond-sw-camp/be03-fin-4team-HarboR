package com.example.harbor_employee.PersonnelAppointment.dto.response;

import com.example.harbor_employee.PersonnelAppointment.domain.PersonnelAppointment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaResDto {
    private String appointmentId;
    private String beforeDepartmentCode;
    private String afterDepartmentCode;
    private String positionCode;
    private String issueDate;
    private String employeeId;
    private String updateDutyCode;

    public static PaResDto toPaDto(PersonnelAppointment personnelAppointment) {
        return PaResDto.builder()
                .appointmentId(String.valueOf(personnelAppointment.getAppointmentId()))
                .beforeDepartmentCode(personnelAppointment.getBeforeDepartmentCode())
                .afterDepartmentCode(personnelAppointment.getAfterDepartmentCode())
                .positionCode(personnelAppointment.getPositionCode())
                .issueDate(personnelAppointment.getIssueDate())
                .employeeId(personnelAppointment.getEmployee().getEmployeeId())
                .updateDutyCode(personnelAppointment.getUpdateDutyCode())
                .build();

    }
}

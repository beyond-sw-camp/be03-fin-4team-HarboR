package com.example.harbor_employee.Employee.dto.response;

import com.example.harbor_employee.Employee.domain.Employee;
import com.example.harbor_employee.client.dto.LoginMemberResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.security.auth.Login;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailResDto {
   private String employeeId;
   private String department;
   private String team;
   private String position;
   private String status;
   private String gender;
   private String duty;
    // 프로필 이미지 : FormData 로 변경해야함
   private String profileImagePath;
   private String email;
   private String name;
   private String birthDate;
   private String careerYMD;
   private String joinDate;
    // 퇴사날짜 및 퇴사사유 : 위의 status에서 퇴사 여부 확인 후 퇴사자의 경우에만 확인할 수 있도록
   private String leavingDate;
   private String reasonForResignation;
    // 주소 : 디테일하게 클래스로 나누는거 고려해보기
   private String address;
   private String phone;
    // 주민등록번호 (저장 시에도 뒤 7자리는 가리기)
   private String socialSecurityNumber;
    // 은행 및 계좌번호 : 본인만 확인할 수 있도록
   private String bank;
   private String accountNumber;

    public static EmployeeDetailResDto toDto(Employee employee) {
        return EmployeeDetailResDto.builder()
                .employeeId(employee.getEmployeeId())
                .department(employee.getDepartmentCode())
                .team(employee.getTeamCode())
                .position(employee.getPositionCode())
                .status(employee.getStatusCode())
                .gender(employee.getGenderCode())
                .duty(employee.getDutyCode())
                .profileImagePath(employee.getProfileImage())
                .email(employee.getEmail())
                .name(employee.getName())
                .birthDate(employee.getBirthDate())
                .careerYMD(employee.getCareerYMD())
                .joinDate(employee.getJoinDate())
                .leavingDate(employee.getLeavingDate())
                .reasonForResignation(employee.getReasonForResignation())
                .address(employee.getAddress())
                .phone(employee.getPhone())
                .socialSecurityNumber(employee.getSocialSecurityNumber())
                .bank(employee.getBankCode())
                .build();
    }
}

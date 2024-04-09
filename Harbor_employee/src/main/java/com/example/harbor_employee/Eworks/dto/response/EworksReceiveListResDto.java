package com.example.harbor_employee.Eworks.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EworksReceiveListResDto {
    private Long payId;
    private String employeeId;
    private String statusCode;

    public static EworksReceiveListResDto create(Long payId, String employeeId, String statusCode){
        return EworksReceiveListResDto.builder()
                .payId(payId)
                .employeeId(employeeId)
                .statusCode(statusCode)
                .build();
    }
}

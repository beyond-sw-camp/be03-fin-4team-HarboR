package com.example.harbor_employee.Eworks.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EworksAuthList {
    private List<String> firstAuthorizer;
    private List<String> secondAuthorizer;
    private List<String> thirdAuthorizer;

    public static EworksAuthList create(List<String> firstAuthorizer, List<String> secondAuthorizer, List<String> thirdAuthorizer){
        return EworksAuthList.builder()
                .firstAuthorizer(firstAuthorizer)
                .secondAuthorizer(secondAuthorizer)
                .thirdAuthorizer(thirdAuthorizer)
                .build();
    }
}

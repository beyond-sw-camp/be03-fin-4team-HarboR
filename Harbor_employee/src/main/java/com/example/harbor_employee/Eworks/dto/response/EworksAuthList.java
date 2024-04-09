package com.example.harbor_employee.Eworks.dto.response;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EworksAuthList {
    private List<Inform> firstAuthorizer;
    private List<Inform> secondAuthorizer;
    private List<Inform> thirdAuthorizer;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Inform{
        String name;
        String employeeId;
        public static Inform create(String name, String employeeId){
            return Inform.builder()
                    .name(name)
                    .employeeId(employeeId)
                    .build();
        }
    }
    public static EworksAuthList create(List<Inform> firstAuthorizer, List<Inform> secondAuthorizer, List<Inform> thirdAuthorizer){
        return EworksAuthList.builder()
                .firstAuthorizer(firstAuthorizer)
                .secondAuthorizer(secondAuthorizer)
                .thirdAuthorizer(thirdAuthorizer)
                .build();
    }
}

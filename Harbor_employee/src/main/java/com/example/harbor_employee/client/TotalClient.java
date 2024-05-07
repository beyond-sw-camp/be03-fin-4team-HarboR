package com.example.harbor_employee.client;


import com.example.harbor_employee.client.dto.EmployeeStatusDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "total-service")
public interface TotalClient {
    @GetMapping(value = "/attendance/status", produces = "application/json", consumes = "application/json")
    List<EmployeeStatusDto> getStatus(@RequestParam("id") List<String> employeeId);
}

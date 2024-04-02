package com.example.harbor_employee.Employee.controller;

import com.example.harbor_employee.Employee.dto.request.EmployeeSearchDto;
import com.example.harbor_employee.Employee.dto.response.EmployeeResDto;
import com.example.harbor_employee.Employee.dto.response.GetEmployResponse;
import com.example.harbor_employee.client.dto.LoginMemberResDto;
import com.example.harbor_employee.global.common.CommonResponse;
import com.example.harbor_employee.Employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/getlist")
    public ResponseEntity<CommonResponse> getList(EmployeeSearchDto employeeSearchDto, Pageable pageable){
        List<EmployeeResDto> employees = employeeService.findAll(employeeSearchDto, pageable);
        return new ResponseEntity<>(new CommonResponse("요청이 정상적으로 실행되었습니다.", employees), HttpStatus.OK);
    }

    @PostMapping("/create")
    public HttpStatus BasicCreateEmployee(@RequestBody LoginMemberResDto loginMemberResDto) {
        log.info(" 호출 시작 : ");
        return employeeService.createBasicEmployee(loginMemberResDto);
    }
    @GetMapping("/admin")
    public ResponseEntity<String> healthCheck(){
        System.out.println("접근 성공");
        return ResponseEntity.status(HttpStatus.OK).body("pong");
    }
    @GetMapping("/{employeeId}/positionCode")
    public ResponseEntity<GetEmployResponse> getPositionCodeByEmployeeId(@PathVariable("employeeId") String employeeId){
        log.info("퐁");
        GetEmployResponse positionCode = employeeService.getUserPosition(employeeId);
        log.info("돌려줌");
        return ResponseEntity.status(HttpStatus.OK).body(positionCode);
    }

}

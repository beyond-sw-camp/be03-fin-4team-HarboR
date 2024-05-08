package com.example.harbor_employee.PersonnelAppointment.controller;

import com.example.harbor_employee.Employee.dto.request.EmployeeSearchDto;
import com.example.harbor_employee.Employee.dto.response.ExcelDataDto;
import com.example.harbor_employee.PersonnelAppointment.service.PersonnelAppointmentService;
import com.example.harbor_employee.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/personnel")
@Slf4j
public class PersonnelAppointmentController {

    private final PersonnelAppointmentService paService;

    public PersonnelAppointmentController(PersonnelAppointmentService paService) {
        this.paService = paService;
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> create(@RequestPart(value = "file") MultipartFile file) throws IOException {
        log.info("{excel 파일 전송 }");
        List<ExcelDataDto> excelDataDtos = paService.create(file);
        return new ResponseEntity<>(new CommonResponse("요청이 정상적으로 실행되었습니다.", excelDataDtos), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<CommonResponse> listAll(EmployeeSearchDto employeeSearchDto, Pageable pageable) {
        return new ResponseEntity<>(new CommonResponse("전체 리스트 호출", paService.findAll(employeeSearchDto, pageable)), HttpStatus.OK);
    }

    @DeleteMapping("/{appointmentId}/delete")
    public ResponseEntity<CommonResponse> deletePA(@PathVariable("appointmentId") Long appointmentId) {
        return new ResponseEntity<>(new CommonResponse("해당 인사발령 취소", paService.delete(appointmentId)), HttpStatus.OK);
    }

    @GetMapping("/{appointmentId}/detail")
    public ResponseEntity<CommonResponse> detailPA(@RequestHeader("appointmentId") Long appointmentId) {
        return new ResponseEntity<>(new CommonResponse("해당 인사발령 자세히 보기", paService.detail(appointmentId)), HttpStatus.OK);
    }
}

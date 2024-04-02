package com.example.harbor_employee.PersonnelAppointment.controller;

import com.example.harbor_employee.Employee.dto.response.ExcelDataDto;
import com.example.harbor_employee.PersonnelAppointment.service.PersonnelAppointmentService;
import com.example.harbor_employee.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee/personnel")
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CommonResponse> create(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("{excel 파일 전송 }");
        List<ExcelDataDto> excelDataDtos = paService.create(file);
        return new ResponseEntity<>(new CommonResponse("요청이 정상적으로 실행되었습니다.", excelDataDtos), HttpStatus.OK);
    }
}

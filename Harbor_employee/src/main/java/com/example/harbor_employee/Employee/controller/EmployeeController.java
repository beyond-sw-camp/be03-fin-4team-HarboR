package com.example.harbor_employee.Employee.controller;

import com.example.harbor_employee.Employee.dto.NameBirthDto;
import com.example.harbor_employee.Employee.dto.request.EmployeeSearchDto;
import com.example.harbor_employee.Employee.dto.request.EmployeeUpdateRequestDto;
import com.example.harbor_employee.Employee.dto.response.EmployeeResDto;
import com.example.harbor_employee.Employee.dto.response.ExcelEmployeeDto;
import com.example.harbor_employee.Employee.dto.response.GetEmployResponse;
import com.example.harbor_employee.client.TotalClient;
import com.example.harbor_employee.client.dto.EmployeeStatusDto;
import com.example.harbor_employee.client.dto.LoginMemberResDto;
import com.example.harbor_employee.Employee.service.EmployeeService;
import com.example.harbor_employee.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TotalClient totalClient;

    public EmployeeController(EmployeeService employeeService, TotalClient totalClient) {
        this.employeeService = employeeService;
        this.totalClient = totalClient;
    }

    /**
     *
     * @param employeeSearchDto: 검색 정보를 담은 DTO(사원 번호, 이름, 부서명, 팀명)
     * @param pageable: 페이지 관리 처리(클라이언트 측에서 보내주기 pageNumber, pageSize, sort)
     * @return HttpStatus: 200 OK, 검색된 리스트 반환
     * @example 아래 형식으로 출력
     * {
     *     "message": "요청이 정상적으로 실행되었습니다.",
     *     "result": [
     *         {
     *             "employeeId": "HB20240001",
     *             "department": "인사 2팀",
     *             "position": "대리 2호봉",
     *             "team": "마케팅팀",
     *             "name": "Berna Phelipeau"
     *         }, ...
     *      ]
     * }
     */
    @GetMapping("/get/list")
    public ResponseEntity<CommonResponse> getList(
            EmployeeSearchDto employeeSearchDto,
            @PageableDefault(page = 0, size = 20, sort = "employeeId", direction = Sort.Direction.ASC) Pageable pageable){
        List<EmployeeResDto> employees = employeeService.findAll(employeeSearchDto, pageable);
        return new ResponseEntity<>(new CommonResponse("전체 리스트 호출", employees), HttpStatus.OK);
    }

    @GetMapping("/get/{employeeId}/detail")
    public ResponseEntity<CommonResponse> getEmployeeDetail(@PathVariable(name = "employeeId") String employeeId) {
        return new ResponseEntity<>(new CommonResponse("유저 정보 자세히 보기", employeeService.findByEmployeeId(employeeId)), HttpStatus.OK);
    }
    /**
     * @param request: 인증 정보에 담긴 name을 이용(employeeId)
     * @return 인증된 사용자의 상세 정보 조회
     */

    //    front에서 admin도 수정 api가 보이는 식으로
    @PatchMapping("/update")
    public ResponseEntity<CommonResponse> updateEmployee(@RequestPart EmployeeUpdateRequestDto request,
                                                         @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        System.out.println(request);
        return new ResponseEntity<>(new CommonResponse("유저 정보 업데이트",employeeService.updateEmployee(request, file)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public HttpStatus BasicCreateEmployee(@RequestBody LoginMemberResDto loginMemberResDto) {
        log.info(" 호출 시작 : ");
        return employeeService.createBasicEmployee(loginMemberResDto);
    }

    @PostMapping("/create/basic")
    public ResponseEntity<CommonResponse> create(@RequestParam("file") MultipartFile file) throws IOException {
        List<ExcelEmployeeDto> excelDataDtos = employeeService.create(file);
        return new ResponseEntity<>(new CommonResponse("요청이 정상적으로 실행되었습니다.", excelDataDtos), HttpStatus.OK);
    }

    @GetMapping("/get/object")
    public NameBirthDto getNameBirth(@RequestHeader(name = "employeeId") String employeeId){
        return employeeService.getObject(employeeId);
    }

    @GetMapping("/positionCode")
    public GetEmployResponse getPositionCodeByEmployeeId(@RequestHeader(name = "employeeId") String employeeId){
        return employeeService.getUserPosition(employeeId);
    }
}

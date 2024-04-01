package com.example.harbor_employee.Employee.controller;

import com.example.harbor_employee.Employee.dto.request.EmployeeSearchDto;
import com.example.harbor_employee.Employee.dto.response.EmployeeResDto;
import com.example.harbor_employee.client.dto.LoginMemberResDto;
import com.example.harbor_employee.global.common.CommonResponse;
import com.example.harbor_employee.Employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

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
    @GetMapping("/getlist")
    public ResponseEntity<CommonResponse> getList(EmployeeSearchDto employeeSearchDto, Pageable pageable){
        List<EmployeeResDto> employees = employeeService.findAll(employeeSearchDto, pageable);
        return new ResponseEntity<>(new CommonResponse("요청이 정상적으로 실행되었습니다.", employees), HttpStatus.OK);
    }

    @GetMapping("/getDetail/{employeeId}")
    public ResponseEntity<CommonResponse> getDetail(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<>(new CommonResponse("요청이 정상적으로 실행되었습니다.", employeeService.findByEmployeeId(employeeId)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public HttpStatus BasicCreateEmployee(@RequestBody LoginMemberResDto loginMemberResDto) {
        log.info(" 호출 시작 : ");
        return employeeService.createBasicEmployee(loginMemberResDto);
    }
}

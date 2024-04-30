package com.example.harbor_employee.Stock;


import com.example.harbor_employee.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/employee")
public class stockController {
    private final StockService stockService;

    public stockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/kospi/all")
    public ResponseEntity<CommonResponse> getKosPiStockList() {
        return new ResponseEntity<>(new CommonResponse("한화 시스템 주가", stockService.getKosPiStockList()), HttpStatus.OK);

    }
}

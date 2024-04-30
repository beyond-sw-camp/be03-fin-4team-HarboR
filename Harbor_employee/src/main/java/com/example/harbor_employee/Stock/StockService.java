package com.example.harbor_employee.Stock;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final JsoupComponent jsoupComponent;

    public StockService(JsoupComponent jsoupComponent) {
        this.jsoupComponent = jsoupComponent;
    }
    public KospiStockDto getKosPiStockList() {
        return jsoupComponent.getKosPiStockList();
    }
}

package com.example.harbor_batch.entity;

import org.springframework.stereotype.Service;

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

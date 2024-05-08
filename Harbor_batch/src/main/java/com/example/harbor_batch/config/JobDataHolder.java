package com.example.harbor_batch.config;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobDataHolder {
    private final List<String> stockList = new ArrayList<>();

    public List<String> getStockList() {
        return stockList;
    }

    public void add(String stockPrice) {
        stockList.add(stockPrice);
    }

    public void clear() {
        stockList.clear();
    }
}

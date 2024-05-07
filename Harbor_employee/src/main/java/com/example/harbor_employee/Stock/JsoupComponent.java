package com.example.harbor_employee.Stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.harbor_employee.Stock.KospiStockDto;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class JsoupComponent {

    public KospiStockDto getKosPiStockList() {
        final String stockList = "https://www.google.com/search?q=%ED%95%9C%ED%99%94%EC%8B%9C%EC%8A%A4%ED%85%9C+%EC%A3%BC%EA%B0%80&oq=%ED%95%9C%ED%99%94%EC%8B%9C%EC%8A%A4%ED%85%9C+%EC%A3%BC%EA%B0%80&gs_lcrp=EgZjaHJvbWUqBggAEEUYOzIGCAAQRRg70gEIMzI0M2owajeoAgCwAgA&sourceid=chrome&ie=UTF-8";
        Connection conn = Jsoup.connect(stockList);
        try {
            Document document = conn.get();
            return getKosPiStockList(document);
        } catch (IOException ignored) {
        }
        return null;
    }

    public KospiStockDto getKosPiStockList(Document document) {
        Elements kosPiTable = document.select("g-card-section div div span span");
        List<KospiStockDto> list = new ArrayList<>();
        String text;
        KospiStockDto kospiStockDto = KospiStockDto.builder().build();
        return KospiStockDto.builder()
                .stockName("한화시스템")
                .price(kosPiTable.get(0).text())
                .befordayprice(kosPiTable.get(3).text())
                .dayRange(kosPiTable.get(4).text())
                .build();
    }
}

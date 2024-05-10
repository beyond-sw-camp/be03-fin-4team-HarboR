package com.example.harbor_batch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KospiStockDto {

    private String stockName;            // 이름
    private String price;                // 현재가
    private String befordayprice;        // 전일대비
    private String dayRange;             // 등락률
}
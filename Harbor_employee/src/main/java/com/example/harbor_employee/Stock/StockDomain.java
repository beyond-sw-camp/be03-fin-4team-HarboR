package com.example.harbor_employee.Stock;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hr_stock")
@Getter
public class Stock {
    private Long stockId;
    private String stockName;            // 이름
    private String price;                // 현재가
    private String befordayprice;        // 전일대비
    private String dayRange;             // 등락률


}




package com.example.harbor_batch.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hr_stock")
@Getter
public class StockDomain {

    @Id
    private Long stockId;

    @Column
    private String stockName;

    @Column
    private String price;

    @Column
    private String befordayprice;

    @Column
    private String dayRange;
}

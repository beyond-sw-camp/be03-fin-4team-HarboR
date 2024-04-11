package com.example.harbor_total.Annual.service;

import com.example.harbor_total.Annual.repository.AnnualRepository;
import org.springframework.stereotype.Service;

@Service

public class AnnualService {
    private final AnnualRepository annualRepository;

    public AnnualService(AnnualRepository annualRepository) {
        this.annualRepository = annualRepository;
    }
}

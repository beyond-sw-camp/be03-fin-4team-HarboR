package com.example.harbor_salary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Getter
@Table(name = "HR_salary")

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Salary{
    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    //직위(코드 번호)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_num", nullable = false)
    private SalaryCode salaryCode;

    //사원 번호
    @Column(nullable = false)
    private int employeeId;

    //해당년월
    @CreationTimestamp
    private LocalDate salaryMonthOfYear;
    //실수령 급여
    @Column(nullable = false)
    private int salaryGross;

    @Builder
    public Salary(Long salaryId, SalaryCode salaryCode, int employeeId, LocalDate salaryMonthOfYear, int salaryBase) {
        this.salaryId = salaryId;
        this.salaryCode = salaryCode;
        this.employeeId = employeeId;
        this.salaryMonthOfYear = salaryMonthOfYear;
        this.salaryGross = salaryGross;
    }

    public static Salary createSalary(Long salaryId, SalaryCode salaryCode, int employeeId, LocalDate salaryMonthOfYear, int salaryBase) {
      return Salary.builder()
              .salaryId(salaryId)
              .salaryCode(salaryCode)
              .employeeId(employeeId)
              .salaryMonthOfYear(salaryMonthOfYear)
              .salaryBase(salaryGross)
              .build();
    }
}

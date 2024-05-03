package com.example.harbor_employee.PersonnelAppointment.repository;

import com.example.harbor_employee.PersonnelAppointment.domain.PersonnelAppointment;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonnelAppointmentRepository extends JpaRepository<PersonnelAppointment, Long> {
    Page<PersonnelAppointment> findAllByDelYn(boolean delYn, Pageable pageable);

    @Query("SELECT p FROM PersonnelAppointment p WHERE p.issueDate >= :today")
    Page<PersonnelAppointment> findAllWithIssueDateAfterToday(@Param("today") String today, Pageable pageable);

};

package com.example.harbor_employee.PersonnelAppointment.repository;

import com.example.harbor_employee.PersonnelAppointment.domain.PersonnelAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelAppointmentRepository extends JpaRepository<PersonnelAppointment, Long> {
    Page<PersonnelAppointment> findAllByDelYn(boolean delYn, Pageable pageable);
};

package com.example.harbor_batch.entity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelAppointmentRepository extends JpaRepository<PersonnelAppointment, Long> {
    Page<PersonnelAppointment> findAllByDelYn(boolean delYn, Pageable pageable);

    @Query("SELECT p FROM PersonnelAppointment p WHERE p.issueDate >= :today")
    Page<PersonnelAppointment> findAllWithIssueDateAfterToday(@Param("today") String today, Pageable pageable);

};

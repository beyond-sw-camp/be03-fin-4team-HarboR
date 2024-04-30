package com.example.harbor_employee.schedule.repository;

import com.example.harbor_employee.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

        Optional<Schedule> findByScheduleId(Long scheduleId);
        List<Schedule> findAllByDelYnIs(boolean delYn);
}

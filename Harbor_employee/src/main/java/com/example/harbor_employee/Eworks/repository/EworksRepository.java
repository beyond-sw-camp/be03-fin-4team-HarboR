package com.example.harbor_employee.Eworks.repository;

import com.example.harbor_employee.Eworks.domain.Eworks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EworksRepository extends JpaRepository<Eworks, Long> {

}

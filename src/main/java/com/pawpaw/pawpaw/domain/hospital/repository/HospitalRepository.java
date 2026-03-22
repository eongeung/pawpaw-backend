package com.pawpaw.pawpaw.domain.hospital.repository;

import com.pawpaw.pawpaw.domain.hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByNameContaining(String name);
}

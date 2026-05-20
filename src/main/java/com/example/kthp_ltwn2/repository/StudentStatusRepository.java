package com.example.kthp_ltwn2.repository;

import com.example.kthp_ltwn2.entity.StudentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentStatusRepository extends JpaRepository<StudentStatus, UUID> {
    Page<StudentStatus> findByIsActiveTrue(Pageable pageable);
    Optional<StudentStatus> findByIdAndIsActiveTrue(UUID id);
}

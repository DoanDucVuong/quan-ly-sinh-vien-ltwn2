package com.example.kthp_ltwn2.repository;

import com.example.kthp_ltwn2.entity.StudentClasseSection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentClasseSectionRepository extends JpaRepository<StudentClasseSection, UUID> {

    Page<StudentClasseSection> findByIsActiveTrue(Pageable pageable);
    Optional<StudentClasseSection> findByIdAndIsActiveTrue(UUID id);
    java.util.List<StudentClasseSection> findByStudentIdAndIsActiveTrue(UUID studentId);
}

package com.example.kthp_ltwn2.repository;

import com.example.kthp_ltwn2.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Page<Student> findByIsActiveTrue(Pageable pageable);

    @Query(value = "SELECT s.* FROM students s WHERE s.is_active = 1 AND " +
           "(s.full_name COLLATE Latin1_General_CI_AI LIKE '%' + :kw + '%' OR " +
           "s.code COLLATE Latin1_General_CI_AI LIKE '%' + :kw + '%')",
           countQuery = "SELECT COUNT(*) FROM students s WHERE s.is_active = 1 AND " +
           "(s.full_name COLLATE Latin1_General_CI_AI LIKE '%' + :kw + '%' OR " +
           "s.code COLLATE Latin1_General_CI_AI LIKE '%' + :kw + '%')",
           nativeQuery = true)
    Page<Student> searchActive(@Param("kw") String keyword, Pageable pageable);

    Optional<Student> findByIdAndIsActiveTrue(UUID id);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}

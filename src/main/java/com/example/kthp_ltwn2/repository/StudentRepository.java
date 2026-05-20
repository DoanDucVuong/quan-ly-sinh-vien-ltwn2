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

    @Query("SELECT s FROM Student s WHERE s.isActive = true AND " +
           "(LOWER(s.fullName) LIKE LOWER(CONCAT('%', :kw, '%')) OR " +
           "LOWER(s.code) LIKE LOWER(CONCAT('%', :kw, '%')))")
    Page<Student> searchActive(@Param("kw") String keyword, Pageable pageable);

    Optional<Student> findByIdAndIsActiveTrue(UUID id);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}

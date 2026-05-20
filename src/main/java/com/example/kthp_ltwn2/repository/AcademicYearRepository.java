package com.example.kthp_ltwn2.repository;

import com.example.kthp_ltwn2.entity.AcademicYear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, UUID> {
    Page<AcademicYear> findByIsActiveTrue(Pageable pageable);

    @Query("SELECT a FROM AcademicYear a WHERE a.isActive = true AND " +
           "(LOWER(a.name) LIKE LOWER(CONCAT('%', :kw, '%')) OR " +
           "LOWER(a.code) LIKE LOWER(CONCAT('%', :kw, '%')))")
    Page<AcademicYear> searchActive(@Param("kw") String keyword, Pageable pageable);

    Optional<AcademicYear> findByIdAndIsActiveTrue(UUID id);
    boolean existsByCode(String code);
}

package com.example.kthp_ltwn2.repository;

import com.example.kthp_ltwn2.entity.Major;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MajorRepository extends JpaRepository<Major, UUID> {
    Page<Major> findByIsActiveTrue(Pageable pageable);

    @Query("SELECT m FROM Major m WHERE m.isActive = true AND " +
           "(LOWER(m.name) LIKE LOWER(CONCAT('%', :kw, '%')) OR " +
           "LOWER(m.code) LIKE LOWER(CONCAT('%', :kw, '%')))")
    Page<Major> searchActive(@Param("kw") String keyword, Pageable pageable);

    Optional<Major> findByIdAndIsActiveTrue(UUID id);
    boolean existsByCode(String code);
    
    List<Major> findByDepartmentIdAndIsActiveTrue(UUID departmentId);
}

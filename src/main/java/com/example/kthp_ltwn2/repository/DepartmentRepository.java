package com.example.kthp_ltwn2.repository;

import com.example.kthp_ltwn2.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    Page<Department> findByIsActiveTrue(Pageable pageable);

    @Query("SELECT d FROM Department d WHERE d.isActive = true AND " +
           "(LOWER(d.name) LIKE LOWER(CONCAT('%', :kw, '%')) OR " +
           "LOWER(d.code) LIKE LOWER(CONCAT('%', :kw, '%')))")
    Page<Department> searchActive(@Param("kw") String keyword, Pageable pageable);

    Optional<Department> findByIdAndIsActiveTrue(UUID id);
    boolean existsByCode(String code);
}

package com.example.kthp_ltwn2.repository;

import com.example.kthp_ltwn2.entity.StudentClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, UUID> {

    Page<StudentClass> findByIsActiveTrue(Pageable pageable);

    @Query("SELECT sc FROM StudentClass sc WHERE sc.isActive = true AND " +
           "(LOWER(sc.name) LIKE LOWER(CONCAT('%', :kw, '%')) OR " +
           "LOWER(sc.code) LIKE LOWER(CONCAT('%', :kw, '%')))")
    Page<StudentClass> searchActive(@Param("kw") String keyword, Pageable pageable);

    Optional<StudentClass> findByIdAndIsActiveTrue(UUID id);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}

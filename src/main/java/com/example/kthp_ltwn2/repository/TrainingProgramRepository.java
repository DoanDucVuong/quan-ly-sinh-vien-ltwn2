package com.example.kthp_ltwn2.repository;

import com.example.kthp_ltwn2.entity.TrainingProgram;
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
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, UUID> {
    Page<TrainingProgram> findByIsActiveTrue(Pageable pageable);

    @Query("SELECT t FROM TrainingProgram t WHERE t.isActive = true AND " +
           "(LOWER(t.name) LIKE LOWER(CONCAT('%', :kw, '%')) OR " +
           "LOWER(t.code) LIKE LOWER(CONCAT('%', :kw, '%')))")
    Page<TrainingProgram> searchActive(@Param("kw") String keyword, Pageable pageable);

    Optional<TrainingProgram> findByIdAndIsActiveTrue(UUID id);
    boolean existsByCode(String code);

    List<TrainingProgram> findByMajorIdAndIsActiveTrue(UUID majorId);
}

package com.example.kthp_ltwn2.repository;

import com.example.kthp_ltwn2.entity.AdvisorClassSection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdvisorClassSectionRepository extends JpaRepository<AdvisorClassSection, UUID> {
    Page<AdvisorClassSection> findByIsActiveTrue(Pageable pageable);
    Optional<AdvisorClassSection> findByIdAndIsActiveTrue(UUID id);
}

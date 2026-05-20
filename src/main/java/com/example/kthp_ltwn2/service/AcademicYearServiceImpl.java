package com.example.kthp_ltwn2.service;
import com.example.kthp_ltwn2.dto.AcademicYearRequest;
import com.example.kthp_ltwn2.dto.AcademicYearResponse;
import com.example.kthp_ltwn2.entity.AcademicYear;
import com.example.kthp_ltwn2.repository.AcademicYearRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AcademicYearServiceImpl implements AcademicYearService {
    private final AcademicYearRepository repository;
    public AcademicYearServiceImpl(AcademicYearRepository repository) { this.repository = repository; }
    @Override
    public Page<AcademicYearResponse> getAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<AcademicYear> entities = (keyword == null || keyword.isEmpty()) ? repository.findByIsActiveTrue(pageable) : repository.searchActive(keyword, pageable);
        return entities.map(this::toResponse);
    }
    @Override
    public AcademicYearResponse getById(UUID id) {
        return repository.findByIdAndIsActiveTrue(id).map(this::toResponse).orElseThrow(() -> new RuntimeException("Academic Year not found"));
    }
    @Override @Transactional
    public AcademicYearResponse create(AcademicYearRequest request) {
        if (repository.existsByCode(request.getCode())) throw new RuntimeException("Code already exists");
        AcademicYear entity = new AcademicYear();
        entity.setCode(request.getCode()); entity.setName(request.getName());
        entity.setStartYear(request.getStartYear()); entity.setEndYear(request.getEndYear());
        return toResponse(repository.save(entity));
    }
    @Override @Transactional
    public AcademicYearResponse update(UUID id, AcademicYearRequest request) {
        AcademicYear entity = repository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("Academic Year not found"));
        entity.setName(request.getName()); entity.setStartYear(request.getStartYear()); entity.setEndYear(request.getEndYear());
        return toResponse(repository.save(entity));
    }
    @Override @Transactional
    public void softDelete(UUID id) {
        AcademicYear entity = repository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("Academic Year not found"));
        entity.setIsActive(false); entity.setDeletedAt(LocalDateTime.now());
        repository.save(entity);
    }
    private AcademicYearResponse toResponse(AcademicYear e) {
        return AcademicYearResponse.builder().id(e.getId()).code(e.getCode()).name(e.getName()).startYear(e.getStartYear()).endYear(e.getEndYear()).build();
    }
}

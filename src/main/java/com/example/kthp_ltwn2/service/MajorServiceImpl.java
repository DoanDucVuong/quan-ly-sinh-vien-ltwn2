package com.example.kthp_ltwn2.service;
import com.example.kthp_ltwn2.dto.MajorRequest;
import com.example.kthp_ltwn2.dto.MajorResponse;
import com.example.kthp_ltwn2.entity.Major;
import com.example.kthp_ltwn2.repository.MajorRepository;
import com.example.kthp_ltwn2.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MajorServiceImpl implements MajorService {
    private final MajorRepository repository;
    private final DepartmentRepository departmentRepository;
    public MajorServiceImpl(MajorRepository repository, DepartmentRepository departmentRepository) { 
        this.repository = repository; 
        this.departmentRepository = departmentRepository;
    }
    @Override
    public Page<MajorResponse> getAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Major> entities = (keyword == null || keyword.isEmpty()) ? repository.findByIsActiveTrue(pageable) : repository.searchActive(keyword, pageable);
        return entities.map(this::toResponse);
    }
    @Override
    public MajorResponse getById(UUID id) {
        return repository.findByIdAndIsActiveTrue(id).map(this::toResponse).orElseThrow(() -> new RuntimeException("Major not found"));
    }
    @Override @Transactional
    public MajorResponse create(MajorRequest request) {
        if (repository.existsByCode(request.getCode())) throw new RuntimeException("Code already exists");
        Major entity = new Major();
        entity.setCode(request.getCode()); entity.setName(request.getName()); entity.setDepartmentId(request.getDepartmentId());
        return toResponse(repository.save(entity));
    }
    @Override @Transactional
    public MajorResponse update(UUID id, MajorRequest request) {
        Major entity = repository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("Major not found"));
        entity.setName(request.getName()); entity.setDepartmentId(request.getDepartmentId());
        return toResponse(repository.save(entity));
    }
    @Override @Transactional
    public void softDelete(UUID id) {
        Major entity = repository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("Major not found"));
        entity.setIsActive(false); entity.setDeletedAt(LocalDateTime.now());
        repository.save(entity);
    }
    private MajorResponse toResponse(Major e) {
        String depName = departmentRepository.findById(e.getDepartmentId()).map(d -> d.getName()).orElse("N/A");
        return MajorResponse.builder().id(e.getId()).code(e.getCode()).name(e.getName()).departmentId(e.getDepartmentId()).departmentName(depName).build();
    }
}

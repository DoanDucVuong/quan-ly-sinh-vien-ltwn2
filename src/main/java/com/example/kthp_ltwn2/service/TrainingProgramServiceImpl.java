package com.example.kthp_ltwn2.service;
import com.example.kthp_ltwn2.dto.TrainingProgramRequest;
import com.example.kthp_ltwn2.dto.TrainingProgramResponse;
import com.example.kthp_ltwn2.entity.TrainingProgram;
import com.example.kthp_ltwn2.repository.TrainingProgramRepository;
import com.example.kthp_ltwn2.repository.MajorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TrainingProgramServiceImpl implements TrainingProgramService {
    private final TrainingProgramRepository repository;
    private final MajorRepository majorRepository;
    public TrainingProgramServiceImpl(TrainingProgramRepository repository, MajorRepository majorRepository) { 
        this.repository = repository; 
        this.majorRepository = majorRepository;
    }
    @Override
    public Page<TrainingProgramResponse> getAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<TrainingProgram> entities = (keyword == null || keyword.isEmpty()) ? repository.findByIsActiveTrue(pageable) : repository.searchActive(keyword, pageable);
        return entities.map(this::toResponse);
    }
    @Override
    public TrainingProgramResponse getById(UUID id) {
        return repository.findByIdAndIsActiveTrue(id).map(this::toResponse).orElseThrow(() -> new RuntimeException("Training Program not found"));
    }
    @Override @Transactional
    public TrainingProgramResponse create(TrainingProgramRequest request) {
        if (repository.existsByCode(request.getCode())) throw new RuntimeException("Code already exists");
        TrainingProgram entity = new TrainingProgram();
        entity.setCode(request.getCode()); entity.setName(request.getName()); entity.setMajorId(request.getMajorId()); entity.setDegreeLevel(request.getDegreeLevel());
        return toResponse(repository.save(entity));
    }
    @Override @Transactional
    public TrainingProgramResponse update(UUID id, TrainingProgramRequest request) {
        TrainingProgram entity = repository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("Training Program not found"));
        entity.setName(request.getName()); entity.setMajorId(request.getMajorId()); entity.setDegreeLevel(request.getDegreeLevel());
        return toResponse(repository.save(entity));
    }
    @Override @Transactional
    public void softDelete(UUID id) {
        TrainingProgram entity = repository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("Training Program not found"));
        entity.setIsActive(false); entity.setDeletedAt(LocalDateTime.now());
        repository.save(entity);
    }
    private TrainingProgramResponse toResponse(TrainingProgram e) {
        String majorName = majorRepository.findById(e.getMajorId()).map(m -> m.getName()).orElse("N/A");
        return TrainingProgramResponse.builder().id(e.getId()).code(e.getCode()).name(e.getName()).majorId(e.getMajorId()).majorName(majorName).degreeLevel(e.getDegreeLevel()).build();
    }
}

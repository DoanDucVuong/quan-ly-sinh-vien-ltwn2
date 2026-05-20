package com.example.kthp_ltwn2.service;
import com.example.kthp_ltwn2.dto.TrainingProgramRequest;
import com.example.kthp_ltwn2.dto.TrainingProgramResponse;
import org.springframework.data.domain.Page;
import java.util.UUID;
public interface TrainingProgramService {
    Page<TrainingProgramResponse> getAll(String keyword, int page, int size);
    TrainingProgramResponse getById(UUID id);
    TrainingProgramResponse create(TrainingProgramRequest request);
    TrainingProgramResponse update(UUID id, TrainingProgramRequest request);
    void softDelete(UUID id);
}

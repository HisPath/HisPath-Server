package com.server.hispath.scholarship.application;

import com.server.hispath.category.application.dto.CategoryContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.scholarship.application.dto.ScholarshipCUDto;
import com.server.hispath.scholarship.application.dto.ScholarshipContentDto;
import com.server.hispath.scholarship.domain.Scholarship;
import com.server.hispath.scholarship.domain.repository.ScholarshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScholarshipService {
    private final ScholarshipRepository scholarshipRepository;

    @Transactional
    public Long apply(ScholarshipCUDto dto){
        Scholarship savedScholarship = scholarshipRepository.save(Scholarship.from(dto));
        return savedScholarship.getId();
    }

    @Transactional
    public List<ScholarshipContentDto> findAll() {
        List<Scholarship> scholarships = scholarshipRepository.findAll();
        return scholarships.stream()
                .map(ScholarshipContentDto::from)
                .collect(Collectors.toList());
    }
}

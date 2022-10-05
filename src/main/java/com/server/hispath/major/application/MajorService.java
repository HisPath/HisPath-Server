package com.server.hispath.major.application;

import com.server.hispath.exception.major.MajorNotFoundException;
import com.server.hispath.major.application.dto.MajorContentDto;
import com.server.hispath.major.application.dto.MajorDto;
import com.server.hispath.major.domain.Major;
import com.server.hispath.major.domain.repository.MajorRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;

    @Transactional
    public Long create(MajorContentDto dto) {
        Major major = Major.from(dto);
        Major savedMajor = majorRepository.save(major);
        return savedMajor.getId();
    }
    @Transactional
    public MajorDto find(Long id) {
        Major major = this.findById(id);
        return MajorDto.from(major);
    }

    @Transactional
    public MajorDto update(Long id, MajorContentDto dto) {
        Major major = this.findById(id);
        major.update(dto);

        return MajorDto.from(major);
    }

    @Transactional
    public void delete(Long id){
        majorRepository.deleteById(id);
    }

    public Major findById(Long id) {
        return majorRepository.findById(id).orElseThrow(MajorNotFoundException::new);
    }

}

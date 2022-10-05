package com.server.hispath.major.application;

import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.category.application.dto.CategoryCUDto;
import com.server.hispath.category.application.dto.CategoryContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.exception.activity.ActivityNotFoundException;
import com.server.hispath.exception.category.CategoryNotFoundException;
import com.server.hispath.major.application.dto.MajorDto;
import com.server.hispath.major.domain.Major;
import com.server.hispath.major.domain.repository.MajorRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MajorService {

    private final MajorRepository majorRepository;

//    @Transactional
//    public Long create(MajorDto dto) {
//        Major savedMajor = majorRepository.save(MajorDto.from(dto));
//        return savedMajor.getId();
//    }
//
//    @Transactional(readOnly = true)
//    public MajorDto find(Long id) {
//        Major major = this.findById(id);
//        return MajorDto.from(major);
//    }
//
//    @Transactional(readOnly = true)
//    public List<CategoryContentDto> findAll() {
//        List<Major> majors = majorRepository.findAll();
//        return majors.stream()
//                .map(MajorDto::from)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public MajorDto update(Long id, MajorDto dto){
//        Major major = MajorRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
//        major.update(dto);
//        return MajorDto.from(major);
//    }
//
//    @Transactional
//    public void delete(Long id){
//        majorRepository.deleteById(id);
//    }
//
//    public Major findById(Long id){
//        return majorRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
//    }
}

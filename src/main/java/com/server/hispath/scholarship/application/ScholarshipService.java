package com.server.hispath.scholarship.application;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.activity.domain.repository.ActivityRepository;
import com.server.hispath.scholarship.application.dto.ScholarshipContentDto;
import com.server.hispath.scholarship.domain.Scholarship;
import com.server.hispath.scholarship.domain.repository.ScholarshipRepository;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.domain.Student;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScholarshipService {
    private final ScholarshipRepository scholarshipRepository;
    private final StudentService studentService;
    private final ActivityRepository activityRepository;

    @Transactional
    public Long create(Long studentId, String semester) {
        Student student = studentService.findById(studentId);

        Scholarship existScholarship = scholarshipRepository.findFirstByStudentAndSemester(student, semester);
        System.out.println("existScholarship = " + existScholarship);
        if(Objects.nonNull(existScholarship)) scholarshipRepository.delete(existScholarship);

        Integer totalWeight = activityRepository.findActivityWithStudents(studentId)
                                                .stream()
                                                .map(Activity::getWeight)
                                                .reduce(0, Integer::sum);

        Scholarship scholarship = Scholarship.builder()
                                             .student(student)
                                             .semester(semester)
                                             .totalMileage(totalWeight)
                                             .sMajor1(student.getMajor1())
                                             .sMajor2(student.getMajor2())
                                             .sDepartment(student.getDepartment())
                                             .build();

        Scholarship savedScholarship = scholarshipRepository.save(scholarship);
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

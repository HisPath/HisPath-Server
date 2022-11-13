package com.server.hispath.scholarship.application;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.dto.ChartRankDto;
import com.server.hispath.activity.application.dto.ChartSearchRequestDto;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.activity.domain.repository.ActivityRepository;
import com.server.hispath.exception.scholarship.ScholarshipDuplicateException;
import com.server.hispath.exception.scholarship.ScholarshipNotFoundException;
import com.server.hispath.exception.scholarship.ScholarshipNotMatchException;
import com.server.hispath.exception.student.StudentNotFoundException;
import com.server.hispath.scholarship.application.dto.ScholarshipApprovalDto;
import com.server.hispath.scholarship.application.dto.ScholarshipContentDto;
import com.server.hispath.scholarship.application.dto.ScholarshipDto;
import com.server.hispath.scholarship.application.dto.SearchRequestDto;
import com.server.hispath.scholarship.domain.Scholarship;
import com.server.hispath.scholarship.domain.repository.ScholarshipRepository;
import com.server.hispath.scholarship.domain.repository.ScholarshipRepositoryCustom;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScholarshipService {
    private final ScholarshipRepository scholarshipRepository;
    private final ScholarshipRepositoryCustom scholarshipRepositoryCustom;
    private final StudentService studentService;
    private final ActivityRepository activityRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public Long create(Long studentId, String semester) {
        Student student = studentService.findById(studentId);

        Optional<Scholarship> existScholarship = scholarshipRepository.findFirstByStudentAndSemester(student, semester);
        existScholarship.ifPresent(scholarshipRepository::delete);

        int totalWeight = getTotalWeight(student, semester);

        Scholarship scholarship = Scholarship.builder()
                                             .student(student)
                                             .semester(semester)
                                             .studentSemester(student.getSemester())
                                             .totalMileage(totalWeight)
                                             .sMajor1(student.getMajor1())
                                             .sMajor2(student.getMajor2())
                                             .sDepartment(student.getDepartment())
                                             .build();

        Scholarship savedScholarship = scholarshipRepository.save(scholarship);
        return savedScholarship.getId();
    }

    private int getTotalWeight(Student student, String semester) {
        return activityRepository.findActivitiesByStudentAndSemester(student, semester)
                                 .stream()
                                 .map(Activity::getWeight)
                                 .reduce(0, Integer::sum);
    }

    @Transactional(readOnly = true)
    public List<ScholarshipContentDto> findAll() {
        List<Scholarship> scholarships = scholarshipRepository.findAll();
        return scholarships.stream()
                           .map(ScholarshipContentDto::from)
                           .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ScholarshipDto> findAllScholarshipStudent(boolean approved, String semester) {
        List<Scholarship> scholarships = scholarshipRepository.findAllByApprovedAndSemester(approved, semester);
        return scholarships.stream()
                           .map(ScholarshipDto::of)
                           .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScholarshipDto findScholarshipStudent(Long studentId, String semester) {
        Scholarship scholarship = scholarshipRepository.findStudentIdAndSemester(studentId, semester)
                                                       .orElseThrow(ScholarshipNotFoundException::new);
        return ScholarshipDto.of(scholarship);
    }

    @Transactional
    public void approveAll(List<ScholarshipApprovalDto> scholarshipApprovalDtos, String semester) {
        validateScholarshipDatas(scholarshipApprovalDtos, semester);
        checkDuplicate(scholarshipApprovalDtos);

        scholarshipApprovalDtos.forEach(dto -> {
            Student student = studentRepository.findByStudentNum(dto.getStudentNum())
                                               .orElseThrow(StudentNotFoundException::new);
            Scholarship scholarship = scholarshipRepository.findFirstByStudentAndSemester(student, semester)
                                                           .orElseThrow(ScholarshipNotFoundException::new);
            scholarship.approve(dto.getResult());
        });


    }

    private void checkDuplicate(List<ScholarshipApprovalDto> scholarshipApprovalDtos) {
        Set<String> set = scholarshipApprovalDtos.stream()
                                                 .map(ScholarshipApprovalDto::StudentInfo)
                                                 .collect(Collectors.toSet());

        if (set.size() < scholarshipApprovalDtos.size()) {
            throw new ScholarshipDuplicateException();
        }
    }

    private boolean validateError(ScholarshipApprovalDto dto, String semester) {
        Optional<Student> optionalStudent = studentRepository.findByStudentNum(dto.getStudentNum());
        if (optionalStudent.isEmpty())
            return true;
        Student student = optionalStudent.get();
        if (!student.isNameMatch(dto.getName()))
            return true;
        Optional<Scholarship> optionalScholarship = scholarshipRepository.findFirstByStudentAndSemester(student, semester);
        if (optionalScholarship.isEmpty())
            return true;
        Scholarship scholarship = optionalScholarship.get();
        if (!scholarship.isStudentSemesterMatch(dto.getStudentSemester()))
            return true;
        return scholarship.getTotalMileage() != dto.getWeight();
    }

    private void validateScholarshipDatas(List<ScholarshipApprovalDto> scholarshipApprovalDtos, String semester) {
        List<ScholarshipApprovalDto> errors = scholarshipApprovalDtos.stream()
                                                                     .filter(dto -> validateError(dto, semester))
                                                                     .collect(Collectors.toList());
        if (errors.size() != 0) {
            String errorMsg = errors.stream()
                                    .map(ScholarshipApprovalDto::toString)
                                    .collect(Collectors.joining("\n"));
            throw new ScholarshipNotMatchException(errorMsg);
        }
    }

    @Transactional(readOnly = true)
    public List<ScholarshipDto> searchScholarshipStudent(SearchRequestDto dto) {
        return scholarshipRepositoryCustom.searchScholarshipStudent(dto)
                                          .stream()
                                          .map(ScholarshipDto::of)
                                          .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ChartRankDto getRankChartData(Long studentId, ChartSearchRequestDto dto) {
        Student student = studentService.findById(studentId);
        int myWeight = scholarshipRepository.findFirstByStudentAndSemester(student, dto.getSemester())
                                            .orElseThrow(ScholarshipNotFoundException::new)
                                            .getTotalMileage();
        Double avgWeight = scholarshipRepositoryCustom.getTotalMileageAvg(dto);
        int maxWeight = activityRepository.sumActivityWeight(dto.getSemester());
        return new ChartRankDto(myWeight, avgWeight, maxWeight);
    }
}

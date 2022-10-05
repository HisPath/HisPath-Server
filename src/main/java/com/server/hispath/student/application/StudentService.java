package com.server.hispath.student.application;

import com.server.hispath.department.application.DepartmentService;
import com.server.hispath.department.domain.Department;
import com.server.hispath.exception.student.StudentNotFoundException;
import com.server.hispath.major.application.MajorService;
import com.server.hispath.student.application.dto.StudentCUDto;
import com.server.hispath.student.application.dto.StudentDto;
import java.util.List;
import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.MActivityService;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.exception.student.StudentDataNotMatchException;
import com.server.hispath.student.application.dto.StudentRefDto;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ActivityService activityService;
    private final MActivityService mActivityService;
    private final MajorService majorService;
    private final DepartmentService departmentService;


    @Transactional
    public Long create(StudentCUDto dto) {
        Department department = departmentService.findById(dto.getDepartmentId());
        Student savedStudent = studentRepository.save(Student.from(dto, department));
        return savedStudent.getId();
    }

    @Transactional
    public void createAll(List<StudentRefDto> dtos) {
        List<Student> students = dtos.stream()
                .map(dto -> {
                    Student student = findById(Long.valueOf(dto.getStudentNum()));
                    return Student.from(dto);
                }).collect(Collectors.toList());
        studentRepository.saveAll(students);
    }

    @Transactional
    public StudentDto find(Long id) {
        Student student = this.findById(id);
        return StudentDto.from(student);
    }

    @Transactional
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        students.stream()
                .forEach(student -> {
                    System.out.println("student.getName() = " + student.getName());

                });
        return students.stream()
                .map(StudentDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentDto update(Long id, StudentCUDto dto) {
        Department department = departmentService.findById(id);
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
//        Major major = majorService.findById(majorId);
        student.update(department, dto);
        return StudentDto.from(student);
    }

    @Transactional
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Transactional
    public void registerParticipants(Long activityId, List<StudentRefDto> studentRefDtos) {
        Activity activity = activityService.findById(activityId);
        mActivityService.deleteAllParticipant(activity);
        studentRefDtos.forEach(dto -> {
            Student student = studentRepository.findByStudentNum(dto.getStudentNum())
                    .orElseThrow(StudentNotFoundException::new);
            if (!student.isNameMatch(dto.getName())) {
                throw new StudentDataNotMatchException(dto.getStudentNum(), dto.getName());
            }
            activity.addParticipant(student);
        });
    }
}

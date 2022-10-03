package com.server.hispath.student.application;

import com.server.hispath.exception.category.CategoryNotFoundException;
import com.server.hispath.exception.student.StudentNotFoundException;
import com.server.hispath.student.application.dto.StudentDto;
import java.util.List;
import com.server.hispath.activity.application.ActivityService;
import com.server.hispath.activity.application.MActivityService;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.exception.student.StudentDataNotMatchException;
import com.server.hispath.exception.student.StudentNotFoundException;
import com.server.hispath.student.application.dto.StudentRefDto;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ActivityService activityService;
    private final MActivityService mActivityService;

    @Transactional
    public Long create(StudentDto dto){
        Student savedStudent = studentRepository.save(Student.from(dto));
        return savedStudent.getId();
    }

    @Transactional
    public StudentDto find(Long id) {
        Student student = this.findById(id);
        return StudentDto.from(student);
    }

    @Transactional
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(StudentDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public StudentDto update(Long id, StudentDto dto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);;
        student.update(dto);
        return StudentDto.from(student);
    }

    @Transactional
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public Student findById(Long id){
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);


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

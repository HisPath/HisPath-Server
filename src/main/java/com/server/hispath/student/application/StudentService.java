package com.server.hispath.student.application;

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

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ActivityService activityService;
    private final MActivityService mActivityService;
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

package com.server.hispath.activity.application;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.dto.ActivityDto;
import com.server.hispath.activity.application.dto.MActivityContentDto;
import com.server.hispath.activity.application.dto.MActivityDetailDto;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.activity.domain.repository.ActivityRepository;
import com.server.hispath.category.application.CategoryService;
import com.server.hispath.category.domain.Category;
import com.server.hispath.common.BaseEntity;
import com.server.hispath.exception.activity.ActivityNotFoundException;
import com.server.hispath.exception.activity.ParticipantNotFoundException;
import com.server.hispath.exception.student.StudentNotFoundException;
import com.server.hispath.student.application.dto.StudentRefDetailDto;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MActivityService {
    private final ActivityRepository activityRepository;
    private final CategoryService categoryService;
    private final ActivityService activityService;
    private final StudentRepository studentRepository;

    @Transactional
    public Long create(MActivityContentDto dto) {
        Category category = categoryService.findById(dto.getCategoryId());
        Activity activity = Activity.from(category, dto);
        Activity savedActivity = activityRepository.save(activity);
        return savedActivity.getId();
    }

    @Transactional
    public void createAll(List<MActivityContentDto> dtos) {
        List<Activity> activities = dtos.stream()
                                        .map(dto -> {
                                            Category category = categoryService.findById(dto.getCategoryId());
                                            return Activity.from(category, dto);
                                        }).collect(Collectors.toList());
        activityRepository.saveAll(activities);
    }

    @Transactional
    public ActivityDto update(Long id, MActivityContentDto dto) {
        Activity activity = activityService.findById(id);
        Category category = categoryService.findById(dto.getCategoryId());
        activity.update(category, dto);
        return ActivityDto.from(activity);
    }

    @Transactional(readOnly = true)
    public List<ActivityDto> findAllBySemester(String semester) {
        List<Activity> activities = activityRepository.findAllBySemesterAndRequestStatus(semester, 1);
        return activities.stream()
                         .map(ActivityDto::from)
                         .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ActivityDto> findAll() {
        List<Activity> activities = activityRepository.findAllByRequestStatus(1);
        return activities.stream()
                         .map(ActivityDto::from)
                         .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAllParticipant(Activity activity) {
        activity.getParticipants().forEach(BaseEntity::deleteContent);
    }

    @Transactional
    public void deleteParticipantById(Long activityId, Long studentId) {
        Activity activity = activityService.findById(activityId);
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        activity.getParticipants()
                .stream()
                .filter(participant -> Objects.equals(participant.getStudent(), student))
                .findFirst().orElseThrow(ParticipantNotFoundException::new)
                .deleteContent();
    }

    @Transactional
    public MActivityDetailDto findDetailActivityInfo(Long activityId) {
        Activity activity = activityRepository.findActivityWithStudents(activityId)
                                              .orElseThrow(ActivityNotFoundException::new);
        List<StudentRefDetailDto> students = activity.getParticipants()
                                                     .stream()
                                                     .map(participant -> {
                                                         return StudentRefDetailDto.of(participant.getStudent());
                                                     })
                                                     .collect(Collectors.toList());
        return MActivityDetailDto.from(activity, students);
    }
}

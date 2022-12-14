package com.server.hispath.activity.application;

import java.util.List;
import java.util.stream.Collectors;

import com.server.hispath.activity.application.dto.*;
import com.server.hispath.activity.application.dto.chart.ChartCategoryDto;
import com.server.hispath.activity.application.dto.chart.ChartDataDto;
import com.server.hispath.activity.application.dto.chart.ChartSectionCntDto;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.activity.domain.repository.ActivityRepository;
import com.server.hispath.activity.domain.repository.ActivityRepositoryCustom;
import com.server.hispath.activity.presentation.response.chart.ChartSectionResponse;
import com.server.hispath.category.application.CategoryService;
import com.server.hispath.category.domain.Category;
import com.server.hispath.category.domain.repository.CategoryRepository;
import com.server.hispath.exception.activity.ActivityNotFoundException;
import com.server.hispath.exception.activity.ParticipantNotFoundException;
import com.server.hispath.exception.student.StudentNotFoundException;
import com.server.hispath.student.domain.Participant;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;
import com.server.hispath.student.domain.repository.StudentRepositoryCustom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final StudentRepository studentRepository;
    private final StudentRepositoryCustom studentRepositoryCustom;
    private final ActivityRepositoryCustom activityRepositoryCustom;

    @Transactional
    public Long create(Long categoryId, ActivityContentDto dto) {
        Category category = categoryService.findById(categoryId);
        Activity activity = Activity.from(category, dto);
        Activity savedActivity = activityRepository.save(activity);
        return savedActivity.getId();
    }

    @Transactional(readOnly = true)
    public ActivityDto find(Long id) {
        Activity activity = this.findById(id);
        return ActivityDto.from(activity);
    }


    @Transactional(readOnly = true)
    public List<ActivityDto> findAll() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream()
                         .map(ActivityDto::from)
                         .collect(Collectors.toList());
    }

    @Transactional
    public ActivityDto update(Long id, Long categoryId, ActivityContentDto dto) {
        Activity activity = this.findById(id);
        Category category = categoryService.findById(categoryId);
        activity.update(category, dto);

        return ActivityDto.from(activity);
    }

    @Transactional
    public void delete(Long activityId) {
        activityRepository.deleteById(activityId);
    }

    @Transactional
    public void deleteStudentActivity(Long studentId, Long activityId) {
        Activity activity = activityRepository.findActivityWithStudents(activityId)
                                              .orElseThrow(ActivityNotFoundException::new);
        Student student = studentRepository.findById(studentId)
                                           .orElseThrow(StudentNotFoundException::new);
        if (activity.isPersonal()) {
            activityRepository.deleteById(activityId);
            return;
        }
        activity.getParticipants()
                .stream()
                .filter(participant -> participant.isSameStudent(student))
                .findFirst()
                .orElseThrow(ParticipantNotFoundException::new)
                .deleteContent();
    }

    @Transactional(readOnly = true)
    public List<SemesterDto> bringSemester() {
        List<String> semesters = activityRepository.bringSemester();
        return semesters.stream()
                        .map(SemesterDto::from)
                        .collect(Collectors.toList());

    }

    @Transactional
    public Long createStudentActivity(Long studentId,
                                      StudentActivityContentDto activityContentDto,
                                      ParticipantContentDto participantContentDto) {

        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Category category = categoryRepository.findByName("??????");
        Activity activity = Activity.from(activityContentDto, category);
        activity.addParticipant(student, participantContentDto);
        return activityRepository.save(activity).getId();
    }

    @Transactional
    public ActivityParticipantDto updateStudentActivity(Long activityId, Long studentId,
                                                        StudentActivityContentDto activityContentDto,
                                                        ParticipantContentDto participantContentDto) {

        Activity activity = this.findById(activityId);
        activity.update(activityContentDto);
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Participant updatedParticipant = activity.getParticipants()
                                                 .stream()
                                                 .filter(participant -> participant.isSameStudent(student))
                                                 .findFirst()
                                                 .orElseThrow(ParticipantNotFoundException::new);
        updatedParticipant.update(participantContentDto);
        return ActivityParticipantDto.of(updatedParticipant);
    }

    @Transactional(readOnly = true)
    public List<ActivityParticipantDto> findAllParticipantActivites(Long studentId, String semester, String section) {
        Student student = studentRepository.findStudentWithActivities(studentId)
                                           .orElseThrow(StudentNotFoundException::new);
        return student.getParticipants()
                      .stream()
                      .filter(participant -> participant.isSameSemester(semester))
                      .filter(participant -> participant.isSameSection(section))
                      .map(ActivityParticipantDto::of)
                      .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ActivityParticipantDto> findRecentParticipantActivities(Long studentId) {

        Student student = studentRepository.findStudentWithActivities(studentId)
                                           .orElseThrow(StudentNotFoundException::new);
        List<ActivityParticipantDto> dtos = student.getParticipants()
                                                   .stream()
                                                   .sorted((p1, p2) -> p2.getUpdatedAt()
                                                                         .compareTo(p1.getUpdatedAt()))
                                                   .map(ActivityParticipantDto::of)
                                                   .collect(Collectors.toList());
        if (dtos.size() > 6) {
            return dtos.subList(0, 6);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public List<ActivityParticipantDto> findAllPersonalParticipantActivites(Long id, String semester, String section) {
        Student student = studentRepository.findStudentWithActivities(id).orElseThrow(StudentNotFoundException::new);
        return student.getParticipants()
                      .stream()
                      .filter(participant -> participant.isSameSemester(semester))
                      .filter(participant -> participant.isSameSection(section))
                      .filter(Participant::isPersonal)
                      .map(ActivityParticipantDto::of)
                      .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ActivityParticipantDto findParticipantActivityById(Long studentId, Long activityId) {
        Activity activity = activityRepository.findActivityWithStudents(activityId)
                                              .orElseThrow(ActivityNotFoundException::new);
        Student student = studentRepository.findById(studentId)
                                           .orElseThrow(StudentNotFoundException::new);
        Participant participant = activity.getParticipants()
                                          .stream()
                                          .filter(p -> p.isSameStudent(student))
                                          .findFirst()
                                          .orElseThrow(ParticipantNotFoundException::new);
        return ActivityParticipantDto.of(participant);

    }

    @Transactional(readOnly = true)
    public List<ActivityDto> findAllByStudentAndSemster(Long id, String semester) {
        Student student = findStudentWholeByIdAndSemester(id, semester, false);

        return student.getParticipants()
                      .stream()
                      .map(participant -> ActivityDto.from(participant.getActivity()))
                      .collect(Collectors.toList());
    }

    @Transactional
    public void apply(Long activityId) {
        this.findById(activityId).apply();
    }

    @Transactional
    public void approve(Long activityId, int weight) {
        this.findById(activityId).approve(weight);
    }

    @Transactional
    public void reject(Long activityId) {
        this.findById(activityId).reject();
    }


    @Transactional(readOnly = true)
    public List<ChartDataDto> getChartDatasByCategory(Long studentId, ChartSearchRequestDto dto) {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        List<ChartCategoryCntDto> studentCnts = activityRepositoryCustom.getStudentCategoryParticipateCnt(student, dto);
        List<ChartCategoryCntDto> totalCnts = activityRepositoryCustom.getTotalCategoryParticipateCnt(dto);
        int totalStudentCnt = activityRepositoryCustom.getTotalStudentCntByChartSearchRequest(dto);

        return totalCnts.stream()
                        .map(totalCnt ->
                                studentCnts.stream()
                                           .filter(studentCnt -> studentCnt.isSameCategory(totalCnt.getCategory()))
                                           .findFirst()
                                           .map(studentCnt -> new ChartDataDto(studentCnt, totalCnt.getCnt(), totalStudentCnt))
                                           .orElseGet(() -> new ChartDataDto(totalCnt.getCategory(), totalCnt.getCnt(), totalStudentCnt))
                        )
                        .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ChartCategoryDto> getChartTotalDatasByCategory(Long studentId, ChartSearchRequestDto dto) {
        List<ChartDataDto> chartDataDtos = this.getChartDatasByCategory(studentId, dto);

        List<ChartCategoryCntDto> activityCntDtos = activityRepositoryCustom.getActivityCntByCategoryAndSemester(dto);

        return chartDataDtos.stream()
                            .map(chartDataDto ->
                                    activityCntDtos.stream()
                                                   .filter(activityCntDto -> activityCntDto.isSameCategory(chartDataDto.getCategory()))
                                                   .findFirst()
                                                   .map(activityCntDto -> new ChartCategoryDto(chartDataDto, activityCntDto.getCnt()))
                                                   .orElseThrow(ActivityNotFoundException::new))
                            .filter(ChartCategoryDto::isNotETC)
                            .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<ChartSectionResponse> getChartTotalDatasBySections(Long studentId, String semester) {
        ChartSearchRequestDto dto = ChartSearchRequestDto.builder().semester(semester).build();
        List<ChartSectionCntDto> studentCnts = activityRepositoryCustom.getPersonalChartSectionCnts(studentId, dto);
        List<ChartSectionCntDto> totalCnts = activityRepositoryCustom.getTotalChartSectionCnts(dto);
        int totalStudentCnt = activityRepositoryCustom.getTotalStudentCntByChartSearchRequest(dto);

        return totalCnts.stream()
                        .map(totalCnt -> {
                            return studentCnts.stream()
                                              .filter(studentCnt -> studentCnt.isSameSection(totalCnt.getSection()))
                                              .findFirst()
                                              .map(studentCntDto -> new ChartSectionResponse(totalCnt.getSection(), studentCntDto.getCnt(), totalCnt.getCnt() / (double) totalStudentCnt))
                                              .orElseGet(() -> new ChartSectionResponse(totalCnt.getSection(), 0L, totalCnt.getCnt() / (double) totalStudentCnt));
                        })
                        .collect(Collectors.toList());

    }

    private Student findStudentWholeByIdAndSemester(Long studentId, String semester, boolean isMileage) {
        return studentRepositoryCustom.findStudentWithIdAndSemester(studentId, semester, isMileage)
                                      .orElseThrow(StudentNotFoundException::new);
    }

    public Activity findById(Long id) {
        return activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
    }
}

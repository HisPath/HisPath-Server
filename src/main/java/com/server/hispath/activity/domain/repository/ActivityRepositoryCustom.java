package com.server.hispath.activity.domain.repository;

import java.util.List;
import java.util.Objects;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.hispath.activity.application.dto.ChartCategoryCntDto;
import com.server.hispath.activity.application.dto.ChartSearchRequestDto;
import com.server.hispath.activity.application.dto.QChartCategoryCntDto;
import com.server.hispath.student.domain.Student;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import static com.server.hispath.activity.domain.QActivity.activity;
import static com.server.hispath.category.domain.QCategory.category;
import static com.server.hispath.student.domain.QParticipant.participant;
import static com.server.hispath.student.domain.QStudent.student;

@Repository
@RequiredArgsConstructor
public class ActivityRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<ChartCategoryCntDto> getStudentCategoryParticipateCnt(Student student, ChartSearchRequestDto dto) {
        return queryFactory.select(new QChartCategoryCntDto(category.id, category.name, activity.count()))
                           .from(activity)
                           .leftJoin(activity.participants, participant)
                           .leftJoin(activity.category, category)
                           .where(studentChartCategoryCondition(student, dto))
                           .groupBy(category)
                           .fetch();

    }

    public List<ChartCategoryCntDto> getTotalCategoryParticipateCnt(ChartSearchRequestDto dto) {
        return queryFactory.select(new QChartCategoryCntDto(category.id, category.name, activity.participants.size()
                                                                                                             .count()))
                           .from(activity)
                           .leftJoin(activity.participants, participant)
                           .leftJoin(participant.activity, activity)
                           .leftJoin(activity.category, category)
                           .where(totalChartCategoryCondition(dto))
                           .groupBy(category)
                           .fetch();
    }

    public List<ChartCategoryCntDto> getActivityCntByCategoryAndSemester(ChartSearchRequestDto dto) {
        return queryFactory.select(new QChartCategoryCntDto(category.id, category.name ,activity.count()) )
                           .from(activity)
                           .leftJoin(activity.category, category)
                           .where(semesterCondition(new BooleanBuilder(), dto))
                           .groupBy(category)
                           .fetch();
    }

    public int getTotalStudentCntByChartSearchRequest(ChartSearchRequestDto dto) {
        return queryFactory.selectDistinct(participant.student.id)
                           .from(activity)
                           .leftJoin(activity.participants, participant)
                           .where(totalChartCategoryCondition(dto))
                           .fetch().size();
    }

    public BooleanBuilder studentChartCategoryCondition(Student student, ChartSearchRequestDto dto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(participant.student.eq(student));
        booleanBuilder = semesterCondition(booleanBuilder, dto);
        booleanBuilder = mileageActivityCondition(booleanBuilder);
        return booleanBuilder;
    }

    public BooleanBuilder totalChartCategoryCondition(ChartSearchRequestDto dto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder = semesterCondition(booleanBuilder, dto);
        booleanBuilder = departmentCondition(booleanBuilder, dto);
        booleanBuilder = mileageActivityCondition(booleanBuilder);
        if (!Objects.isNull(dto.getGrade())) {
            int grade = (dto.getGrade() + 1) / 2 * 2;
            booleanBuilder.and(participant.student.semester.eq(grade - 1).or(participant.student.semester.eq(grade)));
        }
        return booleanBuilder;
    }

    public BooleanBuilder semesterCondition(BooleanBuilder booleanBuilder, ChartSearchRequestDto dto) {
        if (!Objects.isNull(dto.getSemester())) {
            booleanBuilder.and(activity.semester.eq(dto.getSemester()));
        }
        return booleanBuilder;
    }

    public BooleanBuilder departmentCondition(BooleanBuilder booleanBuilder, ChartSearchRequestDto dto) {

        if (!Objects.isNull(dto.getDepartment())) {
            booleanBuilder.and(participant.student.department.name.eq(dto.getDepartment()));
        }

        return booleanBuilder;
    }

    public BooleanBuilder mileageActivityCondition(BooleanBuilder booleanBuilder) {
        booleanBuilder.and(activity.requestStatus.eq(1));
        return booleanBuilder;
    }
}

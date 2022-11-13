package com.server.hispath.scholarship.domain.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.hispath.activity.application.dto.*;
import com.server.hispath.scholarship.application.dto.SearchRequestDto;
import com.server.hispath.scholarship.domain.Scholarship;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import static com.server.hispath.activity.domain.QActivity.activity;
import static com.server.hispath.scholarship.domain.QScholarship.scholarship;
import static com.server.hispath.student.domain.QParticipant.participant;

@Repository
@RequiredArgsConstructor
public class ScholarshipRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<Scholarship> searchScholarshipStudent(SearchRequestDto dto) {
        return queryFactory.select(scholarship)
                           .distinct()
                           .from(scholarship)
                           .leftJoin(scholarship.student).fetchJoin()
                           .leftJoin(scholarship.sDepartment).fetchJoin()
                           .leftJoin(scholarship.sMajor1).fetchJoin()
                           .leftJoin(scholarship.sMajor2).fetchJoin()
                           .where(scholarshipStudentCondition(dto))
                           .fetch();

    }

    public Double getTotalMileageAvg(ChartSearchRequestDto dto) {
        return queryFactory.select(scholarship.totalMileage.avg())
                           .from(scholarship)
                           .where(totalMileageAvgCondition(dto))
                           .fetchOne();
    }

    public List<ChartGradeDataDto> getCountByGradeAndSemester(String semester) {
        return queryFactory.select(new QChartGradeDataDto(scholarship.studentSemester, scholarship.count()))
                           .from(scholarship)
                           .where(scholarship.semester.eq(semester).and(scholarship.approved.eq(true)))
                           .groupBy(scholarship.studentSemester)
                           .fetch();
    }

    public List<ChartDepartmentDataDto> getCountByDepartmentAndSemester(String semester) {
        return queryFactory.select(new QChartDepartmentDataDto(scholarship.sDepartment.name, scholarship.count()))
                           .from(scholarship)
                           .where(scholarship.semester.eq(semester).and(scholarship.approved.eq(true)))
                           .groupBy(scholarship.sDepartment)
                           .fetch();
    }

    public BooleanBuilder totalMileageAvgCondition(ChartSearchRequestDto dto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (!Objects.isNull(dto.getSemester())) {
            booleanBuilder.and(scholarship.semester.eq(dto.getSemester()));
        }
        if (!Objects.isNull(dto.getDepartment())) {
            booleanBuilder.and(scholarship.sDepartment.name.eq(dto.getDepartment()));
        }

        if (!Objects.isNull(dto.getGrade())) {
            int grade = (dto.getGrade() + 1) / 2 * 2;
            booleanBuilder.and(scholarship.studentSemester.eq(grade - 1).or(scholarship.studentSemester.eq(grade)));
        }

        return booleanBuilder;
    }

    public BooleanBuilder scholarshipStudentCondition(SearchRequestDto dto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (!Objects.isNull(dto.getSemester())) {
            booleanBuilder.and(scholarship.semester.in(dto.getSemester().split(",")));
        }
        if (!Objects.isNull(dto.getStudentSemester())) {
            booleanBuilder.and(scholarship.studentSemester.in(Arrays.stream(dto.getStudentSemester().split(","))
                                                                    .map(Integer::parseInt)
                                                                    .collect(Collectors.toList())));
        }
        if (!Objects.isNull(dto.getDepartment())) {
            booleanBuilder.and(scholarship.sDepartment.name.in(dto.getDepartment().split(",")));
        }
        if (!Objects.isNull(dto.getMajor1())) {
            booleanBuilder.and(scholarship.sMajor1.name.in(dto.getMajor1().split(",")));
        }
        if (!Objects.isNull(dto.getMajor2())) {
            booleanBuilder.and(scholarship.sMajor2.name.in(dto.getMajor2().split(",")));
        }
        return booleanBuilder;
    }
}

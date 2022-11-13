package com.server.hispath.scholarship.domain.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.hispath.scholarship.application.dto.SearchRequestDto;
import com.server.hispath.scholarship.domain.Scholarship;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import static com.server.hispath.scholarship.domain.QScholarship.scholarship;

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

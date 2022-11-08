//package com.server.hispath.activity.domain.repository;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import com.querydsl.core.BooleanBuilder;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.server.hispath.activity.application.dto.ChartDataDto;
//import com.server.hispath.activity.application.dto.QChartDataDto;
//import com.server.hispath.scholarship.application.dto.SearchRequestDto;
//import com.server.hispath.scholarship.domain.Scholarship;
//
//import org.springframework.stereotype.Repository;
//
//import lombok.RequiredArgsConstructor;
//
//import static com.server.hispath.activity.domain.QActivity.activity;
//import static com.server.hispath.scholarship.domain.QScholarship.scholarship;
//import static com.server.hispath.student.domain.QParticipant.participant;
//
//@Repository
//@RequiredArgsConstructor
//public class ActivityRepositoryCustom {
//    private final JPAQueryFactory queryFactory;
//
//    public List<ChartDataDto> getStudentCategoryChartDatas(Long studentId, SearchRequestDto dto){
//    return
////            queryFactory.select(new QChartDataDto(activity.category.id, activity.category.name, activity.count());
////            .from(activity)
//        queryFactory.select(new QChartDataDto(activity.category.id, activity.category.name, activity.count()))
//                .from(activity)
//                .leftJoin(activity.participants, participant).fetchJoin()
//                .on(participant.st)
//                .where(activity.participants.as("p"))
//
//
//                       ;
//
//
//
//
//
//    }
//
//
//    public BooleanBuilder studentChartSearchCondition(SearchRequestDto dto) {
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//
//        booleanBuilder.and(activity.participants.)
//
//        if (!Objects.isNull(dto.getSemester())) {
//            booleanBuilder.and(scholarship.semester.in(dto.getSemester().split(",")));
//        }
//        if (!Objects.isNull(dto.getStudentSemester())) {
//            booleanBuilder.and(scholarship.studentSemester.in(Arrays.stream(dto.getStudentSemester().split(","))
//                                                                    .map(Integer::parseInt)
//                                                                    .collect(Collectors.toList())));
//        }
//        if (!Objects.isNull(dto.getDepartment())) {
//            booleanBuilder.and(scholarship.sDepartment.name.in(dto.getDepartment().split(",")));
//        }
//        if (!Objects.isNull(dto.getMajor1())) {
//            booleanBuilder.and(scholarship.sMajor1.name.in(dto.getMajor1().split(",")));
//        }
//        if (!Objects.isNull(dto.getMajor2())) {
//            booleanBuilder.and(scholarship.sMajor2.name.in(dto.getMajor2().split(",")));
//        }
//        return booleanBuilder;
//    }
//}

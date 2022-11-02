package com.server.hispath.student.domain.repository;

import java.util.Objects;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.hispath.student.domain.QStudent;
import com.server.hispath.student.domain.Student;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import static com.server.hispath.activity.domain.QActivity.activity;
import static com.server.hispath.student.domain.QParticipant.participant;
import static com.server.hispath.student.domain.QStudent.student;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryCustom {
    private final JPAQueryFactory queryFactory;


    public Optional<Student> findStudentWithIdAndSemester(Long id, String semester) {
        Student student = queryFactory.select(QStudent.student)
                                      .from(QStudent.student)
                                      .leftJoin(QStudent.student.participants, participant).fetchJoin()
                                      .leftJoin(participant.activity, activity).fetchJoin()
                                      .leftJoin(activity.category).fetchJoin()
                                      .where(studentSearchCondition(id, semester))
                                      .fetchOne();
        return Optional.ofNullable(student);
    }


    public BooleanBuilder studentSearchCondition(Long studentId, String semester) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(student.id.eq(studentId));
        if (!Objects.isNull(semester)) {
            booleanBuilder.and(activity.semester.eq(semester));
        }

        return booleanBuilder;
    }
}

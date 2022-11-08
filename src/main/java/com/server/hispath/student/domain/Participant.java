package com.server.hispath.student.domain;

import java.util.Objects;
import javax.persistence.*;

import com.server.hispath.activity.application.dto.ParticipantContentDto;
import com.server.hispath.activity.domain.Activity;
import com.server.hispath.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE participant SET deleted = true Where id = ?")
public class Participant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activity;

    @Enumerated(EnumType.STRING)
    private Section section;

    String data;

    public Participant(Student student, Activity activity, Section section) {
        this.student = student;
        this.activity = activity;
        this.section = section;
        this.data = "";
    }

    public Participant(Student student, Activity activity, ParticipantContentDto dto) {
        this.student = student;
        this.activity = activity;
        this.section = dto.getSection();
        this.data = dto.getData();
    }

    public boolean isSameSemester(String semester) {
        if (Objects.equals(semester, "ALL"))
            return true;
        return activity.isSameSemester(semester);
    }


    public boolean isSameSection(String section) {
        if (Objects.equals(section, "ALL"))
            return true;
        return Objects.equals(this.section.getName(), section);
    }

    public boolean isSameCategory(String category) {
        if(Objects.equals(category, "ALL"))
            return true;
        return activity.isSameCategory(category);
    }

    public boolean isSameStudent(Student student) {
        return Objects.equals(this.student, student);
    }

    public void update(ParticipantContentDto participantContentDto) {
        this.section = participantContentDto.getSection();
        this.data = participantContentDto.getData();
    }


}

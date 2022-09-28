package com.server.hispath.activity.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.activity.application.dto.MActivityContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.common.BaseEntity;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.participate.Participant;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE activity SET deleted = true Where id = ?")
public class Activity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @NotNull
    private String semester;

    private boolean personal;

    private int requestStatus;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime endDate;

    int weight;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Participant> participants = new ArrayList<>();

    public static Activity from(Category category, ActivityContentDto dto) {
        return Activity.builder()
                       .category(category)
                       .semester(dto.getSemester())
                       .personal(dto.isPersonal())
                       .requestStatus(dto.getRequestStatus())
                       .name(dto.getName())
                       .weight(dto.getWeight())
                       .startDate(dto.getStartDate())
                       .endDate(dto.getEndDate())
                       .build();
    }

    public static Activity from(Category category, MActivityContentDto dto) {
        return Activity.builder()
                       .category(category)
                       .semester(dto.getSemester())
                       .personal(false)
                       .requestStatus(1)
                       .name(dto.getName())
                       .weight(dto.getWeight())
                       .startDate(dto.getStartDate())
                       .endDate(dto.getEndDate())
                       .build();
    }

    public void update(Category category, ActivityContentDto dto) {
        this.category = category;
        this.semester = dto.getSemester();
        this.personal = dto.isPersonal();
        this.requestStatus = dto.getRequestStatus();
        this.name = dto.getName();
        this.weight = dto.getWeight();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
    }

    public void update(Category category, MActivityContentDto dto) {
        this.category = category;
        this.semester = dto.getSemester();
        this.name = dto.getName();
        this.weight = dto.getWeight();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
    }

    public void addParticipant(Student student){
        Participant participant = new Participant(student, this);

        this.participants.add(participant);
        student.addParticipant(participant);
    }
}

package com.server.hispath.activity.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.activity.application.dto.MActivityContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.common.BaseEntity;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.Participant;
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

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST )
    private Category category;

    @NotNull
    private String semester;

    private boolean personal;

    private String remark;

    private int requestStatus;

    private String name;

    private boolean studentRegistered = false;

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
                       .remark(dto.getRemark())
                       .weight(dto.getWeight())
                       .build();
    }

    public static Activity from(Category category, MActivityContentDto dto) {
        return Activity.builder()
                       .category(category)
                       .semester(dto.getSemester())
                       .personal(false)
                       .requestStatus(1)
                       .name(dto.getName())
                       .remark(dto.getRemark())
                       .weight(dto.getWeight())
                       .build();
    }

    public void update(Category category, ActivityContentDto dto) {
        this.category = category;
        this.semester = dto.getSemester();
        this.personal = dto.isPersonal();
        this.requestStatus = dto.getRequestStatus();
        this.name = dto.getName();
        this.remark = dto.getRemark();
        this.weight = dto.getWeight();
    }

    public void update(Category category, MActivityContentDto dto) {
        this.category = category;
        this.semester = dto.getSemester();
        this.name = dto.getName();
        this.remark = dto.getRemark();
        this.weight = dto.getWeight();
    }

    public void addParticipant(Student student) {
        Participant participant = new Participant(student, this);

        this.participants.add(participant);
        student.addParticipant(participant);
    }

    public void updateStudentRegister() {
        studentRegistered = true;
    }
}

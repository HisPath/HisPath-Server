package com.server.hispath.activity.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.*;

import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.activity.application.dto.MActivityContentDto;
import com.server.hispath.activity.application.dto.ParticipantContentDto;
import com.server.hispath.activity.application.dto.StudentActivityContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.common.BaseEntity;
import com.server.hispath.exception.activity.ParticipantDuplicateException;
import com.server.hispath.student.domain.Participant;
import com.server.hispath.student.domain.Section;
import com.server.hispath.student.domain.Student;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Category category;

    @NotNull
    private String semester;

    private boolean personal;

    private String remark;

    // 1 일떄 마일리지 처리, 0이면 마일리지 아님
    private int requestStatus;

    private String name;

    private boolean studentRegistered = false;

    int weight;

    @Builder.Default
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

    public static Activity from(StudentActivityContentDto dto, Category category) {
        return Activity.builder()
                       .category(category)
                       .semester(dto.getSemester())
                       .remark(dto.getRemark())
                       .personal(true)
                       .requestStatus(3)
                       .name(dto.getName())
                       .weight(0)
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

    public void update(StudentActivityContentDto dto) {
        this.semester = dto.getSemester();
        this.name = dto.getName();
        this.remark = dto.getRemark();
    }

    public void addParticipant(Student student, Section section) {
        checkDuplicate(student);
        Participant participant = new Participant(student, this, section);
        this.connectParticipant(student, participant);
    }

    public void checkDuplicate(Student student) {
        Optional<Participant> dupParticipant = this.getParticipants()
                                                   .stream()
                                                   .filter(participant -> participant.isSameStudent(student))
                                                   .findFirst();
        if (dupParticipant.isPresent()) {
            Student duplicatedStudent = dupParticipant.get().getStudent();
            throw new ParticipantDuplicateException(duplicatedStudent.getStudentNum(), duplicatedStudent.getName());
        }
    }

    public void addParticipant(Student student, ParticipantContentDto participantContentDto) {
        Participant participant = new Participant(student, this, participantContentDto);
        this.connectParticipant(student, participant);
    }

    private void connectParticipant(Student student, Participant participant) {
        this.participants.add(participant);
        student.addParticipant(participant);
    }

    public void updateStudentRegister() {
        studentRegistered = true;
    }

    public boolean isSameSemester(String semester) {
        if(Objects.equals(semester, "ALL"))
            return true;
        return Objects.equals(this.semester, semester);
    }

    public boolean isSameCategory(String category) {
        if(Objects.equals(category, "ALL"))
            return true;
        return Objects.equals(this.category.getName(), category);
    }

    public void apply(){
        this.requestStatus = 0;
    }

    public void approve(int weight){
        this.requestStatus = 1;
        this.weight = weight;
    }

    public void reject(){
        this.requestStatus = 2;
    }

    public boolean isParticipate(Student student) {
        return this.participants.stream()
                .anyMatch(participant -> participant.isSameStudent(student));
    }


}

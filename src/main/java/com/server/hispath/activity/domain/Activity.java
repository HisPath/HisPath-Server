package com.server.hispath.activity.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.server.hispath.activity.application.dto.ActivityContentDto;
import com.server.hispath.category.domain.Category;
import com.server.hispath.common.BaseEntity;
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

    @NotNull
    private String data;

    @OneToMany(mappedBy = "activity")
    private List<Participant> participants = new ArrayList<>();

    public static Activity from(Category category, ActivityContentDto dto) {
        return Activity.builder()
                       .category(category)
                       .semester(dto.getSemester())
                       .personal(dto.isPersonal())
                       .requestStatus(dto.getRequestStatus())
                       .data(dto.getData())
                       .build();
    }
}

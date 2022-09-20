package com.server.hispath.category.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.category.application.dto.CategoryContentDto;
import com.server.hispath.common.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Where(clause = "deleted = false")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE category SET deleted = true Where id = ?")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Activity> activities = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    public static Category from(CategoryContentDto dto) {
        return Category.builder()
                       .type(dto.getType())
                       .name(dto.getName())
                       .build();
    }
}

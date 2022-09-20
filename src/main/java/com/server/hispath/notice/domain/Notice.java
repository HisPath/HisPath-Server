package com.server.hispath.notice.domain;

import javax.persistence.*;

import com.server.hispath.manager.domain.Manager;
import com.server.hispath.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE notice SET deleted = true Where id = ?")
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manager writer;

    private String title;

    private String content;

    private int viewCnt;

    private boolean importance;

}

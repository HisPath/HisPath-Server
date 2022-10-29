package com.server.hispath.notice.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.common.BaseEntity;

import com.server.hispath.notice.application.dto.NoticeContentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE notice SET deleted = true Where id = ?")
public class Notice extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manager manager;

    private String title;

    @Column(length = 5555)
    private String content;

    private int viewCnt;

    private boolean importance;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate pubDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate expDate;

    public static Notice from(Manager manager, NoticeContentDto dto) {
        return Notice.builder()
                .manager(manager)
                .title(dto.getTitle())
                .content(dto.getContent())
                .viewCnt(dto.getViewCnt())
                .importance(dto.isImportance())
                .pubDate(dto.getPubDate())
                .expDate(dto.getExpDate())
                .build();
    }

    public void update(Manager manager, NoticeContentDto dto) {
        this.manager = manager;
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.viewCnt = dto.getViewCnt();
        this.importance = dto.isImportance();
        this.pubDate = dto.getPubDate();
        this.expDate = dto.getExpDate();
    }

}

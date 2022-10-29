package com.server.hispath.resume.domain;

import javax.persistence.*;

import com.server.hispath.common.BaseEntity;
import com.server.hispath.resume.application.dto.ResumeDto;
import com.server.hispath.student.domain.Student;

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
@SQLDelete(sql = "UPDATE resume SET deleted = true Where id = ?")
public class Resume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    private String title;

    @Column(length = 5555)
    private String content;

    // ToDo 여러 템플릿을 위한 필드
    // private Template template;

    public void updateContent(ResumeDto resumeDto){
        this.title = resumeDto.getTitle();
        this.content = resumeDto.getContent();
    }
}

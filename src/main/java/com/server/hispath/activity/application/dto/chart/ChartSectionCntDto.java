package com.server.hispath.activity.application.dto.chart;

import java.util.Objects;

import com.querydsl.core.annotations.QueryProjection;
import com.server.hispath.student.domain.Section;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChartSectionCntDto {

    private String section;
    private Long cnt;

    @QueryProjection
    public ChartSectionCntDto(Section section, Long cnt) {
        this.section = section.getName();
        this.cnt = cnt;
    }

    public boolean isSameSection(String section){
        return Objects.equals(this.section, section);
    }
}

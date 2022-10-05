package com.server.hispath.major.application.dto;

import com.server.hispath.major.domain.Major;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MajorDto {
    private Long id;
    private String majorName;

    public static MajorDto from(Major major) {
        return new MajorDto(major.getId(), major.getName());
    }
}

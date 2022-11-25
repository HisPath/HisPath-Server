package com.server.hispath.major.application.dto;

import com.server.hispath.major.domain.Major;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MajorDto {
    private Long id;
    private String name;
    private String profile;
    public static MajorDto from(Major major) {
        return new MajorDto(major.getId(), major.getName(), major.getProfile());
    }
}
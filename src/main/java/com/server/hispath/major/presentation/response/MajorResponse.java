package com.server.hispath.major.presentation.response;


import com.server.hispath.major.application.dto.MajorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MajorResponse {
    private Long id;
    private String majorName;

    public static MajorResponse from(MajorDto dto) {
        return new MajorResponse(dto.getId(), dto.getMajorName());
    }
}

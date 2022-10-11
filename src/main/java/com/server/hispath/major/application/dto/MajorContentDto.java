package com.server.hispath.major.application.dto;

import com.server.hispath.major.presentation.request.MajorCURequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MajorContentDto {

    private String majorName;

    public static MajorContentDto from(MajorCURequest request) {
        return new MajorContentDto(request.getMajorName());
    }
}

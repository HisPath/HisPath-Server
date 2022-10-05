package com.server.hispath.scholarship.application.dto;


import com.server.hispath.scholarship.presentation.request.ScholarshipCURequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScholarshipCUDto {
    private String semester;

    public static ScholarshipCUDto of(ScholarshipCURequest request) { return new ScholarshipCUDto(request.getSemester());}
}

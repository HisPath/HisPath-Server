package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.student.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDto {
    private String semester;

    public static SemesterDto from(String semester) {
        return new SemesterDto(semester);
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof SemesterDto){
            return semester.equals(((SemesterDto) o).semester);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return semester.hashCode();
    }


}




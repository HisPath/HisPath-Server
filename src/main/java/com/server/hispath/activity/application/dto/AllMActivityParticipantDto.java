package com.server.hispath.activity.application.dto;

import com.server.hispath.activity.domain.Activity;
import com.server.hispath.category.application.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllMActivityParticipantDto {
    private Long id;
    private String semester;
    private String name;
    private String remark;
    private CategoryDto category;
    private boolean participated;

    public static AllMActivityParticipantDto of(Activity activity, boolean participated) {
        return new AllMActivityParticipantDto(activity.getId(), activity.getSemester(), activity.getName(), activity.getRemark(), CategoryDto.from(activity.getCategory()),
                 participated);
    }
//    public boolean getParticipated(){
//        return participated;
//    }
}


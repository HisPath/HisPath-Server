package com.server.hispath.category.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCURequest {
    private Long studentId;
    private String name;
}

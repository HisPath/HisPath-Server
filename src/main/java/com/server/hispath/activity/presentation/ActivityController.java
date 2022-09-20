package com.server.hispath.activity.presentation;

import com.server.hispath.activity.application.ActivityService;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;
}

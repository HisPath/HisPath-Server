package com.server.hispath.activity.application;

import com.server.hispath.activity.domain.repository.ActivityRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

}

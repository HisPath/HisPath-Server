package com.server.hispath.auth.domain;

import java.util.Objects;

public enum Member {
    STUDENT, MANAGER;

    public static boolean isStudent(Member member){
        return member.equals(STUDENT);
    }

    public static boolean isManager(Member member){
        return member.equals(MANAGER);
    }
}

package com.server.hispath.auth.domain;

public enum Member {
    STUDENT, MANAGER, ALL, SUPER_MANAGER;

    public static boolean isStudent(Member member) {
        return member.equals(STUDENT);
    }

    public static boolean isManager(Member member) {
        return member.equals(MANAGER);
    }

    public static boolean isSuperManager(Member member) {
        return member.equals(SUPER_MANAGER);
    }

    public static boolean isALl(Member member) {return member.equals(ALL);}
}

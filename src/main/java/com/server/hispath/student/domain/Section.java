package com.server.hispath.student.domain;

public enum Section {
    REWARD("수상"), SKILL("기술"), COURSE("과정"),LINK("링크"),
    INTERNSHIP("인턴"), CERTIFICATE("자격증"), LANGUAGE("언어"), ETC("기타");

    private String name;

    Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

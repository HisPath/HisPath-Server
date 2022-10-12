package com.server.hispath.student.domain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.server.hispath.exception.student.SectionNotExistException;

public enum Section {
    REWARD("수상"), SKILL("기술"), COURSE("과정"), LINK("링크"),
    INTERNSHIP("인턴"), CERTIFICATE("자격증"), LANGUAGE("언어"), ETC("기타");

    private String name;

    Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static final Map<String, Section> map = Collections.unmodifiableMap(Stream.of(values())
                                                                                      .collect(Collectors.toMap(Section::getName, Function.identity())));
    public static Section find(String name){
        return Optional.ofNullable(map.get(name)).orElseThrow(SectionNotExistException::new);
    }
}

package com.server.hispath.student.domain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.server.hispath.exception.student.SectionNotExistException;

public enum Section {
     EXPERIENCE("경력"), DEGREE("학력"),SKILL("기술"),  CERTIFICATE("자격증"),REWARD("수상"), LANGUAGE("외국어"),
    LINK("링크"), ETC("기타");

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

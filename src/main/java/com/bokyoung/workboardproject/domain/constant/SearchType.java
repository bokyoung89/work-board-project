package com.bokyoung.workboardproject.domain.constant;

import lombok.Getter;

public enum SearchType {
    TITLE("제목"),
    CONTENT("본문"),
    ID("ID"),
    NICKNAME("닉네임"),
    HASHTAG("#");

    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }
}

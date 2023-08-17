package com.bokyoung.workboardproject.domain.constant;

import lombok.Getter;

public enum FormStatus {
    CREATE("등록", false),
    UPDATE("수정", true);

    @Getter
    private final String description;
    @Getter private final Boolean update;

    FormStatus(String description, Boolean update) {
        this.description = description;
        this.update = update;
    }
}

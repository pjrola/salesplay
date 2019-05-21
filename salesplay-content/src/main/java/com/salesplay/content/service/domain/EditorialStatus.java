package com.salesplay.content.service.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.salesplay.content.service.converter.EnumConverter;
import com.salesplay.content.service.util.PersistableEnum;

public enum EditorialStatus implements PersistableEnum<String> {
    PUBLISHED("published"),
    REVIEW("review"),
    DRAFT("draft"),
    APPROVED("approved"),
    ARCHIVE("archive"),
    REJECTED("rejected");

    private final String value;

    EditorialStatus(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String getValue() {
        return value;
    }

    public static class Converter extends EnumConverter<EditorialStatus, String> {
        public Converter() {
            super(EditorialStatus.class);
        }
    }
}
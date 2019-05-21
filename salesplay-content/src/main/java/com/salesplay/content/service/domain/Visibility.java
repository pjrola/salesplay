package com.salesplay.content.service.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.salesplay.content.service.converter.EnumConverter;
import com.salesplay.content.service.util.PersistableEnum;

public enum Visibility implements PersistableEnum<String> {
    PUBLIC("public"),
    PRIVATE("private");

    private final String value;

    Visibility(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String getValue() {
        return value;
    }

    public static class Converter extends EnumConverter<Visibility, String> {
        public Converter() {
            super(Visibility.class);
        }
    }
}

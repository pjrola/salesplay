package com.salesplay.content.service.converter;

import com.salesplay.content.service.util.PersistableEnum;

import javax.persistence.AttributeConverter;

public abstract class EnumConverter<T extends Enum<T> & PersistableEnum<E>, E> implements AttributeConverter<T, E> {
    private final Class<T> clazz;

    public EnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public T convertToEntityAttribute(E dbData) {
        T[] enums = clazz.getEnumConstants();

        for (T e : enums) {
            if (e.getValue().toString().equalsIgnoreCase(dbData.toString())) {
                return e;
            }
        }

        throw new UnsupportedOperationException("Enum not found");
    }
}
package com.salesplay.content.service.dto;

public interface DTOMapper<E, D> {
    D mapToDto(E entity);
    E mapFromDto(D dto);
}

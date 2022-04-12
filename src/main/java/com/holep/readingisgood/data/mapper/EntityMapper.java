package com.holep.readingisgood.data.mapper;

public interface EntityMapper<E, D> {
    D toDTO(E e);
    E toEntity(D d);
}

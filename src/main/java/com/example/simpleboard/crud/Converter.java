package com.example.simpleboard.crud;

public interface Converter<DTO, ENTITY> {

    DTO toDto(ENTITY entity); //dto로 변환하는 메서드

    ENTITY toEntity(DTO dto); //엔터티로 변환하는 메서드
}

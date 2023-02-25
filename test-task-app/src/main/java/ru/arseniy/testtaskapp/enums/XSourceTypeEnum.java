package ru.arseniy.testtaskapp.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum XSourceTypeEnum {
    MAIL(List.of("name", "email")),
    MOBILE(List.of("phoneNumber")),
    BANK(List.of("bankId", "surname", "name", "patronymic", "birthDate", "passportNumber")),
    GOSUSLUGI(List.of("bankId", "name", "surname", "patronymic", "birthDate", "passportNumber", "birthPlace", "phoneNumber", "registrationAddress"));

    XSourceTypeEnum(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    private final List<String> requiredFields;

}

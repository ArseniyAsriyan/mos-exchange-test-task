package ru.arseniy.testtaskapp.exceptions;

import ru.arseniy.testtaskapp.enums.XSourceTypeEnum;

public class RequestValidationException extends RuntimeException {
    public RequestValidationException() {
        super("Необходимо передать как минимум один из следующих параметров для поиска - name, surname, patronymic, phoneNumber, email");
    }

    public RequestValidationException(XSourceTypeEnum xSourceType) {
        super("Отправляя запрос с " + xSourceType + " обязательные к заполнению поля - " + xSourceType.getRequiredFields());
    }
}

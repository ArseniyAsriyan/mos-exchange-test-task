package ru.arseniy.testtaskapp.exceptions;

import ru.arseniy.testtaskapp.dto.CustomerDto;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        super("Клиент с ID - " + customerId + " не найден.");
    }

    public CustomerNotFoundException(CustomerDto customerDto) {
        super("Клиент по параметрам - " + customerDto + " не найден.");
    }
}

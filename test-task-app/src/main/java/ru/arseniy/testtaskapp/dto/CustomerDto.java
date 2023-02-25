package ru.arseniy.testtaskapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CustomerDto {
    private String bankId;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;
    private String passportNumber;
    private String birthPlace;
    private String phoneNumber;
    private String email;
    private String registrationAddress;
    private String residentialAddress;
}

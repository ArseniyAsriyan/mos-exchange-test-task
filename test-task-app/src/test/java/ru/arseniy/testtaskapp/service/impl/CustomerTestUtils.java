package ru.arseniy.testtaskapp.service.impl;

import org.mapstruct.factory.Mappers;
import ru.arseniy.testtaskapp.dto.CustomerDto;
import ru.arseniy.testtaskapp.entity.CustomerEntity;
import ru.arseniy.testtaskapp.mappers.CustomerMapper;

import java.time.LocalDate;

public class CustomerTestUtils {

    private static final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    protected static CustomerEntity getCustomerEntity() {
        var entity = new CustomerEntity();
        entity.setId(55L);
        entity.setBankId("sberbank-138854");
        entity.setName("Vavilen");
        entity.setSurname("Tatarskiy");
        entity.setPatronymic("Erastovich");
        entity.setBirthDate(LocalDate.of(1984, 3, 9));
        entity.setPassportNumber("3800 644156");
        entity.setBirthPlace("USSR Moscow");
        entity.setPhoneNumber("9032564437");
        entity.setEmail("generation-p@arscorp.com");
        entity.setRegistrationAddress("Lohmatyh generalov street 32-111");
        entity.setResidentialAddress("Krestyanskaya zastava 1-1");

        return entity;
    }

    protected static CustomerDto getCustomerDto(CustomerEntity customerEntity) {
        return mapper.entityToDto(customerEntity);
    }
}

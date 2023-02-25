package ru.arseniy.testtaskapp.mappers;

import org.mapstruct.Mapper;
import ru.arseniy.testtaskapp.dto.CustomerDto;
import ru.arseniy.testtaskapp.entity.CustomerEntity;

@Mapper
public interface CustomerMapper {
    CustomerEntity dtoToEntity(CustomerDto customerDto);
    CustomerDto entityToDto(CustomerEntity customerEntity);
}

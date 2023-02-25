package ru.arseniy.testtaskapp.repository;

import ru.arseniy.testtaskapp.dto.CustomerDto;
import ru.arseniy.testtaskapp.entity.CustomerEntity;

import java.util.List;

public interface CriteriaCustomerRepository {
    List<CustomerEntity> findCustomerByRepresentedData(CustomerDto customerDto);
}

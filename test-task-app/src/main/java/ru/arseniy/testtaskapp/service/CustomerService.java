package ru.arseniy.testtaskapp.service;

import ru.arseniy.testtaskapp.dto.BaseResponse;
import ru.arseniy.testtaskapp.dto.CustomerDto;
import ru.arseniy.testtaskapp.enums.XSourceTypeEnum;

import java.util.List;

public interface CustomerService {
    BaseResponse addCustomer(XSourceTypeEnum xSourceType, CustomerDto customer) throws Exception;
    CustomerDto getCustomerById(Long customerId);
    List<CustomerDto> findByCustomerData(CustomerDto customer);
}

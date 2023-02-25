package ru.arseniy.testtaskapp.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.arseniy.testtaskapp.dto.CustomerDto;
import ru.arseniy.testtaskapp.entity.CustomerEntity;
import ru.arseniy.testtaskapp.enums.XSourceTypeEnum;
import ru.arseniy.testtaskapp.exceptions.CustomerNotFoundException;
import ru.arseniy.testtaskapp.exceptions.RequestValidationException;
import ru.arseniy.testtaskapp.repository.CustomerRepository;
import ru.arseniy.testtaskapp.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @Test
    @SneakyThrows
    void addCustomerSuccess() {
        CustomerService customerService = new CustomerServiceImpl(repository);
        var customerDto = CustomerDto.builder()
                .name("Testname")
                .email("testmail@arscorp.com")
                .build();
        var response = customerService.addCustomer(XSourceTypeEnum.MAIL, customerDto);
        ArgumentCaptor<CustomerEntity> argCaptor = ArgumentCaptor.forClass(CustomerEntity.class);
        Mockito.verify(repository, Mockito.times(1)).saveAndFlush(argCaptor.capture());
        var result = argCaptor.getValue();

        Assertions.assertTrue(response.isSuccess());
        Assertions.assertEquals(customerDto.getName(), result.getName());
        Assertions.assertEquals(customerDto.getEmail(), result.getEmail());
    }

    @Test
    void addCustomerIncompatibleRepresentedDataAndXSourceType() {
        CustomerService customerService = new CustomerServiceImpl(repository);
        var customerDto = CustomerDto.builder()
                .surname("Pupkin")
                .email("testmail@arscorp.com")
                .build();

        Assertions.assertThrows(RequestValidationException.class, () -> customerService.addCustomer(XSourceTypeEnum.MAIL, customerDto));
    }

    @Test
    void getCustomerByIdSuccess() {
        var id = 55L;
        var expectedEntity = CustomerTestUtils.getCustomerEntity();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(expectedEntity));
        CustomerService customerService = new CustomerServiceImpl(repository);

        Assertions.assertEquals(CustomerTestUtils.getCustomerDto(expectedEntity), customerService.getCustomerById(id));
    }

    @Test
    void getCustomerByIdFail() {
        var id = 55L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());
        CustomerService customerService = new CustomerServiceImpl(repository);

        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(id));
    }

    @Test
    void findByCustomerDataSuccess() {
        var customerDto = CustomerDto.builder()
                .name("Vavilen")
                .build();
        var expected = CustomerTestUtils.getCustomerEntity();
        Mockito.when(repository.findCustomerByRepresentedData(customerDto)).thenReturn(List.of(CustomerTestUtils.getCustomerEntity()));
        CustomerService customerService = new CustomerServiceImpl(repository);
        var result = customerService.findByCustomerData(customerDto);

        Assertions.assertEquals(CustomerTestUtils.getCustomerDto(expected), result.get(0));
    }

    @Test
    void findByCustomerDataFail() {
        var customerDto = CustomerDto.builder()
                .name("Vavilen")
                .build();
        Mockito.when(repository.findCustomerByRepresentedData(customerDto)).thenReturn(new ArrayList<>());
        CustomerService customerService = new CustomerServiceImpl(repository);

        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.findByCustomerData(customerDto));
    }
}
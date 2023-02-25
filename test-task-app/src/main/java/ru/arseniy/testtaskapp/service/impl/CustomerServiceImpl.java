package ru.arseniy.testtaskapp.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arseniy.testtaskapp.dto.BaseResponse;
import ru.arseniy.testtaskapp.dto.CustomerDto;
import ru.arseniy.testtaskapp.enums.XSourceTypeEnum;
import ru.arseniy.testtaskapp.exceptions.CustomerNotFoundException;
import ru.arseniy.testtaskapp.exceptions.RequestValidationException;
import ru.arseniy.testtaskapp.mappers.CustomerMapper;
import ru.arseniy.testtaskapp.repository.CustomerRepository;
import ru.arseniy.testtaskapp.service.CustomerService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public BaseResponse addCustomer(XSourceTypeEnum xSourceType, CustomerDto customer) throws Exception {
        Field field = null;
        try {
            for (String requiredField : xSourceType.getRequiredFields()) {
                field = customer.getClass().getDeclaredField(requiredField);
                field.setAccessible(true);
                var fieldValue = field.get(customer);
                field.setAccessible(false);
                if (fieldValue == null) {
                    throw new RequestValidationException(xSourceType);
                }
            }
        } finally {
            if (Objects.nonNull(field)) {
                field.setAccessible(false);
            }
        }

        repository.saveAndFlush(mapper.dtoToEntity(customer));

        return BaseResponse.builder()
                .Success(true)
                .build();
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        var customer = repository.findById(customerId);

        return mapper.entityToDto(
                customer.orElseThrow(() -> new CustomerNotFoundException(customerId))
        );
    }

    @Override
    public List<CustomerDto> findByCustomerData(CustomerDto customer) {
        validateFindByCustomerRequest(customer);

        var entities = repository.findCustomerByRepresentedData(customer);
        if (entities.size() == 0) {
            throw new CustomerNotFoundException(customer);
        }

        return entities.stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());
    }

    private void validateFindByCustomerRequest(CustomerDto customer) {
        if (Objects.nonNull(customer.getName()) &&
                Objects.nonNull(customer.getSurname()) &&
                Objects.nonNull(customer.getPatronymic()) &&
                Objects.nonNull(customer.getPhoneNumber()) &&
                Objects.nonNull(customer.getEmail())) {
            throw new RequestValidationException();
        }
    }

}

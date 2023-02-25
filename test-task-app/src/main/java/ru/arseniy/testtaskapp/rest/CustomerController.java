package ru.arseniy.testtaskapp.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.arseniy.testtaskapp.dto.BaseResponse;
import ru.arseniy.testtaskapp.dto.CustomerDto;
import ru.arseniy.testtaskapp.enums.XSourceTypeEnum;
import ru.arseniy.testtaskapp.service.CustomerService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService requestValidationService) {
        this.customerService = requestValidationService;
    }

    @PostMapping
    public BaseResponse addCustomer(@RequestHeader("x-Source") XSourceTypeEnum xSourceHeader,
                                    CustomerDto customer) throws Exception {
        log.debug("addCustomer(x-Source - {}, customer - {})", xSourceHeader, customer);
        var response = customerService.addCustomer(xSourceHeader, customer);
        log.debug("addCustomer response - {}", response);
        return response;
    }

    @GetMapping
    public List<CustomerDto> getCustomer(@RequestParam(value = "name", required = false) String name,
                                         @RequestParam(value = "surname", required = false) String surname,
                                         @RequestParam(value = "patronymic", required = false) String patronymic,
                                         @RequestParam(value = "phone-number", required = false) String phoneNumber,
                                         @RequestParam(value = "e-mail", required = false) String email) {
        log.debug("getCustomer(name - {}, surname - {}, patronymic - {}, phoneNumber - {}, email - {})", name, surname, patronymic, phoneNumber, email);
        var result = customerService.findByCustomerData(
                CustomerDto.builder()
                        .name(name)
                        .surname(surname)
                        .patronymic(patronymic)
                        .phoneNumber(phoneNumber)
                        .email(email)
                        .build()
        );
        log.debug("getCustomer() result - {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable("id") Long id) {
        log.debug("getCustomerById(id - {})", id);
        var result = customerService.getCustomerById(id);
        log.debug("getCustomerById() result - {}", result);
        return result;
    }
}

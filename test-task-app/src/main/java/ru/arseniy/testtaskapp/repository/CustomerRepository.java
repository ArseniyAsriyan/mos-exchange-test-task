package ru.arseniy.testtaskapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arseniy.testtaskapp.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CriteriaCustomerRepository {
}

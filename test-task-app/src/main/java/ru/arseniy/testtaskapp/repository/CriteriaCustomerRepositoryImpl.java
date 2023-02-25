package ru.arseniy.testtaskapp.repository;

import lombok.SneakyThrows;
import ru.arseniy.testtaskapp.dto.CustomerDto;
import ru.arseniy.testtaskapp.entity.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CriteriaCustomerRepositoryImpl implements CriteriaCustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @SneakyThrows
    public List<CustomerEntity> findCustomerByRepresentedData(CustomerDto customerDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CustomerEntity> query = cb.createQuery(CustomerEntity.class);
        Root<CustomerEntity> customer = query.from(CustomerEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        for (Field field : customerDto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            var fieldValue = field.get(customerDto);
            if (Objects.nonNull(fieldValue)) {
                Path<String> path = customer.get(field.getName());
                predicates.add(cb.like(path, (String) fieldValue));
            }
        }
        query.select(customer).where(cb.or(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query)
                .getResultList();
    }
}

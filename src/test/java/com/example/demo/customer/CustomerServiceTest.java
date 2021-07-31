package com.example.demo.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void getCustomers() {
        // given
        Customer nene = new Customer(
                1L,
                "el nene",
                "hello",
                "elnene@gmail.com");

        Customer nena = new Customer(
                2L,
                "la nena",
                "hello",
                "lanena@gmail.com");

        customerRepository.saveAll(Arrays.asList(nene, nena));

        // when
        List<Customer> customers = underTest.getCustomers();

        // then
        assertEquals(2, customers.size());

    }

    @Test
    void getCustomer() {
        // given
        Customer nene = new Customer(
                1L,
                "el nene",
                "hello",
                "elnene@gmail.com");

        customerRepository.save(nene);

        // when
        Customer actualCustomer = underTest.getCustomer(nene.getId());

        // then
        // assertEquals(nene, actualCustomer); diferentes probablemente por la posición diferente en memoria
        assertEquals("el nene", actualCustomer.getName());
        assertEquals("hello", actualCustomer.getPassword());
        assertEquals("elnene@gmail.com", actualCustomer.getEmail());
    }
}
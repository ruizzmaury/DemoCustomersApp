package com.example.demo.customer;

import com.example.demo.exception.ApiRequestException;
import com.example.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j // te crea el LOGGER
public class CustomerService {

    // private final static Logger LOGGER = LoggerFactory.getLogger(CustomerService.class); //
                                        // Logger (framework) es una interfaz
                                        // en el caso de querer cambiar de implementación
                                        // bastaría con cambiar el tipo/import Logger
    
    private final CustomerRepository customerRepository;


    List<Customer> getCustomers() {
        log.info("getCustomers was called");
        return customerRepository.findAll();
    }
    

    Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> {

                            NotFoundException notFoundException = new NotFoundException(
                                    "customer with id " + id + " not found");
                            log.error("error in getting customer {}", id, notFoundException);
                            return notFoundException;
                        });
    }
}




package edu.ICET.service;

import edu.ICET.dto.CustomerDto;
import edu.ICET.entity.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerDto customerDto);

        List<CustomerDto> getAllCustomers();
    }

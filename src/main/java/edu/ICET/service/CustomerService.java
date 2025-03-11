package edu.ICET.service;

import edu.ICET.dto.CustomerDto;
import edu.ICET.entity.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto searchByContactOrNic (String string);

    void deleteCustomerById(String customerId);

    void updateCustomer(CustomerDto customerDto);

    List<CustomerDto> searchByText(String text);

     CustomerDto getCuatomerById(String customerId);
}

package edu.ICET.service.impl;

import edu.ICET.dto.CustomerDto;
import edu.ICET.entity.Customer;
import edu.ICET.repocitory.CustomerRepocitory;
import edu.ICET.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    final CustomerRepocitory customerRepocitory;
    final ModelMapper mapper;

    @Override
    public void addCustomer(CustomerDto customerDto) {

        customerRepocitory.save(mapper.map(customerDto,Customer.class));

    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerList = new ArrayList<>();
        List<Customer> all = customerRepocitory.findAll();

        all.forEach(customer -> {
            customerList.add(mapper.map(customer,CustomerDto.class));
        });

        return customerList;
    }
}

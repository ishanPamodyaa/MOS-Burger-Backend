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


    @Override
    public CustomerDto searchByContactOrNic(String string) {

        CustomerDto customer = new CustomerDto();

        Customer customer1 = customerRepocitory.findByContactNumber(string);
        Customer customer2 = customerRepocitory.findByNic(string);

        if( customer1 != null){
            return mapper.map(customer1 ,CustomerDto.class);
        }
       if( customer2 != null){
           return  mapper.map(customer2,CustomerDto.class);
       }

     return  null;
    }

    @Override
    public void deleteCustomerById(String customerId) {
        customerRepocitory.deleteByCustomerId(customerId);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {

        customerRepocitory.save(mapper.map(customerDto , Customer.class));
    }

    @Override
    public List<CustomerDto> searchByText(String text) {

        List<CustomerDto> customerSearchRsult = new ArrayList<>();

        List<Customer> customerList1 = customerRepocitory.findByFstName(text);

        List<Customer> customerList2 = customerRepocitory.findByLstName(text);

        List<Customer> customerList3 = customerRepocitory.findByCity(text);


        customerList1.forEach(customer -> {
            customerSearchRsult.add(mapper.map(customer , CustomerDto.class));
        });
        customerList2.forEach(customer -> {
            customerSearchRsult.add(mapper.map(customer , CustomerDto.class));
        });
        customerList3.forEach(customer -> {
            customerSearchRsult.add(mapper.map(customer , CustomerDto.class));
        });

        return customerSearchRsult;
    }

    @Override
    public CustomerDto getCuatomerById(String customerId) {
        return mapper.map(customerRepocitory.findByCustomerId(customerId),CustomerDto.class);
    }
}

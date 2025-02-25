package edu.ICET.controller;


import edu.ICET.dto.CustomerDto;
import edu.ICET.entity.Customer;
import edu.ICET.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {


    final CustomerServiceImpl customerService;

    @PostMapping("/add")
    public void addCustomer(@RequestBody CustomerDto customerDto){

        customerService.addCustomer(customerDto);
    }


    @GetMapping("/getAll")
    public List<CustomerDto> getAllCustomers (){
        return customerService.getAllCustomers();
    }



}

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

    @GetMapping("/search-by-contactNumberOrNic/{string}")
    public CustomerDto searchByContactOrNic (@PathVariable String string){
        return  customerService.searchByContactOrNic(string);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCuatomerById(@PathVariable Integer id){
        customerService.deleteCustomerById(id);
    }

    @PutMapping("/update")
    public  void updateCustomer(@RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerDto);
    }

    @GetMapping("/search-by-text/{text}")
    List<CustomerDto> searchByText(@PathVariable String text){
        System.out.println(text);
        return customerService.searchByText(text);
    }


}

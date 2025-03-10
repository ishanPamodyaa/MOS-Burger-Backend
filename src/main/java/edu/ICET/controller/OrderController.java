package edu.ICET.controller;


import edu.ICET.dto.OrderDto;
import edu.ICET.entity.Order;
import edu.ICET.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    final OrderService orderService;

    @PostMapping("/add")
    public void addOrder(@RequestBody OrderDto orderDto){
        orderService.addOrder(orderDto);
    }

    @GetMapping("/getOrderById")
    public OrderDto getOrder(@RequestParam String orderId){
        return orderService.getOrder(orderId);
    }

    @GetMapping("/getAll")
    public List<OrderDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder (@PathVariable String orderId){
         orderService.deleteOrder(orderId);
    }

    @GetMapping("/search-by-name/{name}")
    public OrderDto getOrderByName(@PathVariable String name ){
        return orderService.getOrderByName(name);
    }

    @GetMapping("/search-by-contactNumber/{contactNumber}")
    public OrderDto getOrderContactNumber(String contactNumber){
        return orderService.getOrderContactNumber(contactNumber);
    }

}

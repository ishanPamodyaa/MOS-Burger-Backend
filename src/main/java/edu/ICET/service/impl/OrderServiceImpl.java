package edu.ICET.service.impl;

import edu.ICET.dto.CustomerDto;
import edu.ICET.dto.OrderDetailsDto;
import edu.ICET.dto.OrderDto;
import edu.ICET.dto.ProductDto;
import edu.ICET.entity.*;
import edu.ICET.repocitory.CustomerRepocitory;
import edu.ICET.repocitory.OrderDetsilsRepocitory;
import edu.ICET.repocitory.OrderRepocitory;
import edu.ICET.repocitory.ProductRepocitory;
import edu.ICET.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepocitory orderRepocitory;
    final ProductRepocitory productRepocitory;
    final OrderDetsilsRepocitory orderDetsilsRepocitory;
    final CustomerRepocitory customerRepocitory;
    final ModelMapper mapper;

    @Override
    public void addOrder(OrderDto orderDto) {
        if (orderDto.getOrderDetailsDto() == null || orderDto.getOrderDetailsDto().isEmpty()) {
            throw new RuntimeException("Order must contain at least one product");
        }

        Order order = mapper.map(orderDto, Order.class);
        List<OrderDetail> orderDetail = new ArrayList<>();
    
        // Validate all products exist before processing
        for (OrderDetailsDto orderDetailsDto : orderDto.getOrderDetailsDto()) {
            Product product = productRepocitory.findByProductId(orderDetailsDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + orderDetailsDto.getProductId()));
            
            // Validate product quantity
            if (product.getQtyInHand() < orderDetailsDto.getOrderQty()) {
                throw new RuntimeException("Insufficient stock for product: " + orderDetailsDto.getProductId());
            }
        }
    
        // Process order details
        for (OrderDetailsDto orderDetailsDto : orderDto.getOrderDetailsDto()) {
            Product product = productRepocitory.findByProductId(orderDetailsDto.getProductId()).get();

            OrderDetails_pk orderDetailsPk = new OrderDetails_pk(orderDto.getOrderId(), orderDetailsDto.getProductId());
            OrderDetail orderDetailObj = new OrderDetail();
            orderDetailObj.setId(orderDetailsPk);
            orderDetailObj.setOrder(order);
            orderDetailObj.setProduct(product);
            orderDetailObj.setOrderQty(orderDetailsDto.getOrderQty());
            orderDetailObj.setDiscount(orderDetailsDto.getDiscount());
            orderDetailObj.setPrice(orderDetailsDto.getPrice());

            orderDetail.add(orderDetailObj);
        }

    order.setOrderDetails(orderDetail);
    orderRepocitory.save(order);

     Optional<Customer> customerOpt = customerRepocitory.findByCustomerId(orderDto.getCustomerId());

     CustomerDto customerDto =  mapper.map(customerOpt , CustomerDto.class);
     List<String> orderIdList = new ArrayList<>() ;
     orderIdList.add(orderDto.getOrderId());
     customerDto.setOrderIds(orderIdList);
        System.out.println("Customer DTo  " + customerDto);
     customerRepocitory.save(mapper.map(customerDto ,Customer.class));

     List<OrderDetail> orderDetailList = orderDetsilsRepocitory.findByOrderOrderId(orderDto.getOrderId());

     orderDetailList.forEach(orderDetail1 -> {
        ProductDto productdto = mapper.map(productRepocitory.findByProductId(orderDetail1.getId().getProductId()),ProductDto.class);
        productdto.setQtyInHand(productdto.getQtyInHand()-orderDetail1.getOrderQty());
        productRepocitory.save( mapper.map(productdto,Product.class));
     });



    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderResults = new ArrayList<>();
        List<Order> results = orderRepocitory.findAll();
        results.forEach(order -> {
            orderResults.add(mapper.map(order ,OrderDto.class));
        });
        return orderResults;
    }

    @Override
    public OrderDto getOrder(String  orderId) {

        OrderDto orderDto = mapper.map(orderRepocitory.findByOrderId(orderId) ,OrderDto.class) ;

        CustomerDto customerDto = mapper.map(customerRepocitory.findByCustomerId(orderDto.getCustomerId()) , CustomerDto.class);
        orderDto.setCustomerDto(customerDto);
        List <OrderDetail> orderDetailList = orderDetsilsRepocitory.findByOrderOrderId(orderId);

        List <OrderDetailsDto> orderDetailsDtoList =  orderDetailList.stream()
                .map(orderDetail -> mapper.map(orderDetail , OrderDetailsDto.class))
                .collect(Collectors.toList());

        orderDto.setOrderDetailsDto(orderDetailsDtoList);

        return orderDto;
    }

    @Override
    public OrderDto getOrderByName(String name) {
        return null;
    }

    @Override
    public OrderDto getOrderContactNumber(String contactNumber) {
        return null;
    }

    @Override
    public void deleteOrder(String  orderId) {
        orderRepocitory.deleteById(orderId);
    }


}

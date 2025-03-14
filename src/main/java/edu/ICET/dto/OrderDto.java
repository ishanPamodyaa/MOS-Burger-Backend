package edu.ICET.dto;

import edu.ICET.entity.Customer;
import edu.ICET.entity.Order;
import edu.ICET.entity.OrderDetail;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class OrderDto {


    private String orderId;
    private Date orderDate;
    private Integer totalItems;
    private Double totalPrice;
    private Double totalDiscount;
    private Double billValue;
    private String customerId;

    private CustomerDto customerDto;
    private List<OrderDetailsDto> orderDetailsDto;

}

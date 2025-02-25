package edu.ICET.dto;

import edu.ICET.entity.Customer;
import edu.ICET.entity.Order;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class OrderDto {

    private Integer id;
    private String orderId;

    private Date orderDate;
    private Integer totalItems;

    private Double totalPrice;
    private Double totalDiscount;
    private Double billValue;
    private Integer customerId;
    private List<ProductDto> products;

}

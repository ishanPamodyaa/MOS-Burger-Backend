package edu.ICET.dto;


import lombok.Data;

@Data
public class OrderDetailsDto {

    private String productId;
    private String orderId;
    private Integer orderQty;
    private Double price;
    private Double discount;
}

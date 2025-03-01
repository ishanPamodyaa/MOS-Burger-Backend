package edu.ICET.dto;


import lombok.Data;

@Data
public class OrderDetailsDto {

    private Integer productId;
    private String productName;
    private Integer orderQty;
    private Double price;
    private Double discount;
}

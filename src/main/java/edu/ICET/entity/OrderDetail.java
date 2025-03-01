package edu.ICET.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderDetail")
public class OrderDetail {


    private Double price;
    private Double discount;
    private Integer orderQty;
    @ManyToOne
    @JoinColumn(name="orderId", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name="productID", nullable = false)
    private Product product;


}

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

    @EmbeddedId
    private OrderDetails_pk id;

    private Double price;
    private Double discount;
    private Integer orderQty;

    // Map the orderId from the composite key to the order relationship
    @ManyToOne
    @MapsId("orderId")  // Maps to the "orderId" field in OrderDetails_pk
    private Order order;

    // Map the productId from the composite key to the product relationship
    @ManyToOne
    @MapsId("productId")  // Maps to the "productId" field in OrderDetails_pk
    private Product product;
}

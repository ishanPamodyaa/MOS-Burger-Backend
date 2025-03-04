package edu.ICET.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@IdClass(OrderDetails_pk.class)
@Table(name = "orderDetail")
public class OrderDetail {

    @EmbeddedId
    private OrderDetails_pk id;
    private Double price;
    private Double discount;
    private Integer orderQty;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name="orderId", nullable = false)
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name="productId", nullable = false)
    private Product product;
}

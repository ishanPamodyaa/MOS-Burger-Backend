package edu.ICET.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderId;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    private Integer totalItems;

    private Double totalPrice;
    private Double totalDiscount;
    private Double billValue;

    @ManyToOne
    @JoinColumn(name ="customerId", nullable = false)
    private Customer customer;

    @ManyToMany
    @JoinTable(name="order_product",
        joinColumns = @JoinColumn(name="order_id"),
        inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> products ;



}

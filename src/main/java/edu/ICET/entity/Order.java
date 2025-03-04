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
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    private Integer totalItems;

    private Double totalPrice;
    private Double totalDiscount;
    private Double billValue;

    @ManyToOne
    @JoinColumn(name ="customer_id", nullable = false)
    private Customer customer;

  @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL, orphanRemoval = true)
  private  List<OrderDetail> orderDetail;



}

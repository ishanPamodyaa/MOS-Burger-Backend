package edu.ICET.entity;


import edu.ICET.utill.ProductType;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productId;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private String productName;
    private Double price;
    private  Double discount;
    private  Integer qtyInHand;

    @Lob
    private byte[] productImage;

    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;


}

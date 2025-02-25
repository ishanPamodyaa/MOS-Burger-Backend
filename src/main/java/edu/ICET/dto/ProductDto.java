package edu.ICET.dto;

import edu.ICET.entity.Order;
import edu.ICET.utill.ProductType;
import jakarta.persistence.Lob;
import lombok.Data;

import java.util.Date;
import java.util.List;



@Data
public class ProductDto {

    private Integer id;
    private String productId;
    private ProductType productType;
    private String productName;
    private Double price;
    private  Double discount;
    private  Integer qtyInHand;
    private byte[] productImage;
    private Date expireDate;
    private List<Integer> orderIds;
}

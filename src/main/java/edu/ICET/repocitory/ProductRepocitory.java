package edu.ICET.repocitory;

import edu.ICET.entity.Product;
import edu.ICET.utill.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepocitory extends JpaRepository<Product , String> {

    List<Product> findByProductType(ProductType productType);
    List<Product> findByProductName(String productName);
    Optional<Product> findByProductId (String productId);

    void deleteByProductId (String productId);


}

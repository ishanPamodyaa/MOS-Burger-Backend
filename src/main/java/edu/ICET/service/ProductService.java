package edu.ICET.service;

import edu.ICET.dto.ProductDto;
import edu.ICET.utill.ProductType;

import java.util.List;

public interface ProductService {
    void addProduct(ProductDto productDto);

    List<ProductDto> getAllProducts();

    List <ProductDto> searchByProductType(ProductType productType);

    void deleteProductById(Integer id);

    void updateProduct(ProductDto productDto);

    List<ProductDto> searchByName(String name);

    ProductDto searchById(Integer id);

    boolean isAvailable(Integer id);
}

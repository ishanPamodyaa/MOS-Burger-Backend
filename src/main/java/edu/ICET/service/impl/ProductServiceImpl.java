package edu.ICET.service.impl;

import edu.ICET.dto.ProductDto;
import edu.ICET.entity.Product;
import edu.ICET.repocitory.ProductRepocitory;
import edu.ICET.service.ProductService;
import edu.ICET.utill.ProductType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final ProductRepocitory productRepocitory;
    final ModelMapper mapper;

    @Override
    public void addProduct(ProductDto productDto) {
        productRepocitory.save(mapper.map(productDto, Product.class));
    }

    @Override
    public List <ProductDto> getAllProducts() {
        List<ProductDto> productSearchResult = new ArrayList<>();

        List<Product> all = productRepocitory.findAll();

        all.forEach(product -> {
            productSearchResult.add(mapper.map(product, ProductDto.class));
        });
        return productSearchResult;
    }

    @Override
    public List<ProductDto> searchByProductType(ProductType productType) {

        System.out.println(productType);
        List <ProductDto> productSearchResult = new ArrayList<>();

        List <Product> result = productRepocitory.findByProductType(productType);
//        System.out.println("service    "+result);
        result.forEach(product -> {
            productSearchResult.add(mapper.map(product,ProductDto.class));
        });

        return productSearchResult;
    }

    @Override
    public void deleteProductById(String productId) {

        productRepocitory.deleteByProductId(productId);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        productRepocitory.save(mapper.map(productDto ,Product.class));
    }

    @Override
    public List<ProductDto> searchByName(String name) {
        List<ProductDto> productSearchResult = new ArrayList<>();
        List <Product> result = productRepocitory.findByProductName(name);

        result.forEach(product -> {
            productSearchResult.add(mapper.map(product ,ProductDto.class));
        });

        return productSearchResult;
    }

    @Override
    public ProductDto searchById(String productId) {
        return mapper.map(productRepocitory.findByProductId(productId) , ProductDto.class );
    }

    @Override
    public boolean isAvailable(String productId) {
        return productRepocitory.findByProductId(productId).get().getQtyInHand() != 0;
    }
//    @Override
//    public boolean isExpired(Integer id) {
//        return productRepocitory.findById(id).get().getQtyInHand() != 0;
//    }
}

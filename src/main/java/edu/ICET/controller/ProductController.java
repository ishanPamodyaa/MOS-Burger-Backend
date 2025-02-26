package edu.ICET.controller;

import edu.ICET.dto.CustomerDto;
import edu.ICET.dto.ProductDto;
import edu.ICET.service.ProductService;
import edu.ICET.utill.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    final ProductService productService;

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDto productDto){

        productService.addProduct(productDto);
    }
    @GetMapping("/getAll")
    public List<ProductDto> getAllProducts (){
        return productService.getAllProducts();
    }

    @GetMapping("/search-by-ProductType/{productType}")
    public List <ProductDto> searchByProductType (@PathVariable ProductType productType){
        System.out.println(productType);
        return  productService.searchByProductType(productType);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Integer id){
        productService.deleteProductById(id);
    }

    @PutMapping("/update")
    public  void updateProduct(@RequestBody ProductDto productDto){
        productService.updateProduct(productDto);
    }

    @GetMapping("/search-by-name/{name}")
    List<ProductDto> searchByName(@PathVariable String name){
        System.out.println(name);
        return productService.searchByName(name);
    }

    @GetMapping("/search-by-id/{id}")
    public ProductDto searchById (@PathVariable Integer id){
        return  productService.searchById(id);
    }

    @GetMapping("/{id}")
    public boolean isAvailable(@PathVariable Integer id){
        return productService.isAvailable(id);
    }



}

package com.product_supplier.productsupplierservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product_supplier.productsupplierservice.dto.ProductDTO;
import com.product_supplier.productsupplierservice.service.ProductService;

@RestController
public class ProductController {


    @Autowired
    ProductService productService;


    @GetMapping("/get-all-products")
    public List<ProductDTO> getProductNameAndPrice(){
        return productService.getProductNameAndPrice();
    }

    @GetMapping("/search-by-name")
    public List<ProductDTO> getProductListByName(@RequestParam("product_name") String product_name){
        return productService.getProductListByName(product_name);
    }
    

    @GetMapping("/productName")
    public List<String> getProductsName(){
        return productService.getProductsName();
    }

    @GetMapping("/product-without-supplier")
    public List<String> getProductnameWithoutSupplier(){
        return productService.getProductNameWithoutSuppliers();
    }


    
}

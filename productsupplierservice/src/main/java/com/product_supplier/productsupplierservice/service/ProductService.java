package com.product_supplier.productsupplierservice.service;



import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.product_supplier.productsupplierservice.dto.ProductDTO;
import com.product_supplier.productsupplierservice.entity.Product;
import com.product_supplier.productsupplierservice.repository.ProductRepository;

@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;


    public List<ProductDTO> getProductNameAndPrice(){

        List<ProductDTO> productdtos = new ArrayList<>();
        List<Object[]> querList = productRepository.findByProductNameAndBasePrice();

        for(Object[] product: querList){
            String product_name = (String) product[0];
            Double product_baseprice = (Double) product[1];
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProduct_name(product_name);
            productDTO.setProduct_baseprice(product_baseprice);
            productdtos.add(productDTO);
        }
        return productdtos;
    }
    
    public List<ProductDTO> getProductListByName(String product_name){
        return productRepository.findByProductName(product_name);

    }
    
    public List<String> getProductsName(){
        return productRepository.findbyProductname();
    }

    public Product getAllData(int id){
        return productRepository.findById(id).orElseThrow();
    }

    public Product getProductByName(String product_name){
        return productRepository.findByName(product_name);

    }

    public List<String> getProductNameWithoutSuppliers(){
        return productRepository.findByProductNameWithoutSuppliers();
    }
    
}

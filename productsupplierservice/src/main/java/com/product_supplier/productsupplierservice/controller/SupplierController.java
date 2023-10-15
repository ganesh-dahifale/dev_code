package com.product_supplier.productsupplierservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.product_supplier.productsupplierservice.service.SupplierService;

@RestController
public class SupplierController {


    @Autowired
    SupplierService supplierService;
    
    @GetMapping("/suppliers")
    public List<String> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }
}

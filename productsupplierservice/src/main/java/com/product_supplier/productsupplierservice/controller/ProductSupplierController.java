package com.product_supplier.productsupplierservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.product_supplier.productsupplierservice.dto.ProductAssignmentDTO;
import com.product_supplier.productsupplierservice.dto.SupplierAssignmentDTO;
import com.product_supplier.productsupplierservice.service.ProductSupplierService;

@RestController
public class ProductSupplierController {

    @Autowired
    ProductSupplierService productSupplierService;

    @PostMapping("/assign-suppliers")
    public ResponseEntity<String> assignSuppliers(@RequestBody ProductAssignmentDTO assignmentDTOs) {
        return  productSupplierService.assignSuppliers(assignmentDTOs);
    }

    @GetMapping("/action/{product_name}")
    public ResponseEntity<ProductAssignmentDTO> getSupplierAndPrice(@PathVariable String product_name){
        return productSupplierService.getProductSuppliers(product_name);
    }

    @PutMapping("/update/{product_name}")
    public ResponseEntity<String> editSupplierAndPrice(@PathVariable String product_name, @RequestBody List<SupplierAssignmentDTO> supplierAssignments){
        return productSupplierService.updateSupplierAndPrice(product_name, supplierAssignments);
    }
    




}

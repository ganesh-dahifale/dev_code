package com.product_supplier.productsupplierservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_supplier.productsupplierservice.entity.Supplier;
import com.product_supplier.productsupplierservice.repository.SupplierRepository;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public List<String> getAllSuppliers(){
        return supplierRepository.findBySupplierName();
    }

    public Supplier getSupplier(String supplier_name){
        return supplierRepository.findBySupplierName(supplier_name);
    }
    
}

package com.product_supplier.productsupplierservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product_supplier.productsupplierservice.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier,Integer>{

    @Query("SELECT s.supplier_name from Supplier s")
    List<String> findBySupplierName();


    

    @Query("SELECT s FROM Supplier s WHERE s.supplier_name = :supplier_name")
    Supplier findBySupplierName(@Param("supplier_name") String supplier_name);

}


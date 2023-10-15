package com.product_supplier.productsupplierservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product_supplier.productsupplierservice.entity.ProductSupplier;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Integer>{



    

    @Query("SELECT ps FROM ProductSupplier ps WHERE ps.product.product_id = :productId")
    List<ProductSupplier> findByProduct(@Param("productId") int productId);

    //@Query("SELECT ps FROM ProductSupplier p WHERE ps.product_name = :product_name")
    //ProductSupplier findByProduct(String product_name);

    

    


    
    
}

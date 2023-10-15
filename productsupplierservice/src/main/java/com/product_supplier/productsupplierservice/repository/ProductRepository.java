package com.product_supplier.productsupplierservice.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.product_supplier.productsupplierservice.dto.ProductDTO;
import com.product_supplier.productsupplierservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    @Query("SELECT p.product_name,p.product_baseprice FROM Product p")
    List<Object[]> findByProductNameAndBasePrice();

    @Query("SELECT new com.product_supplier.productsupplierservice.dto.ProductDTO(p.product_name, p.product_baseprice) FROM Product p WHERE p.product_name LIKE %:product_name%")
    List<ProductDTO> findByProductName(@Param("product_name") String product_name);

    @Query("Select p.product_name from Product p")
    List<String> findbyProductname();

    @Query("SELECT p FROM Product p WHERE p.product_name = :product_name")
    Product findByName(@Param("product_name") String product_name);

    @Query("SELECT p.product_name FROM Product p " +
           "WHERE p.product_id NOT IN (SELECT ps.product.product_id FROM ProductSupplier ps)")
    List<String> findByProductNameWithoutSuppliers();

    
}

package com.product_supplier.productsupplierservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.product_supplier.productsupplierservice.dto.ProductAssignmentDTO;
import com.product_supplier.productsupplierservice.dto.SupplierAssignmentDTO;
import com.product_supplier.productsupplierservice.entity.Product;
import com.product_supplier.productsupplierservice.entity.ProductSupplier;
import com.product_supplier.productsupplierservice.entity.Supplier;
import com.product_supplier.productsupplierservice.repository.ProductRepository;
import com.product_supplier.productsupplierservice.repository.ProductSupplierRepository;
import com.product_supplier.productsupplierservice.repository.SupplierRepository;

@Service
public class ProductSupplierService {

    @Autowired
    ProductSupplierRepository productSupplierRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public ResponseEntity<String> assignSuppliers(@RequestBody ProductAssignmentDTO assignmentDTOs) {
        try {
            for (SupplierAssignmentDTO supplierAssignmentDTO : assignmentDTOs.getSupplierAssignments()) {
                Product product = productRepository.findByName(assignmentDTOs.getProduct_name());
                Supplier supplier = supplierRepository.findBySupplierName(supplierAssignmentDTO.getSupplierName());

                // if (productSupplierRepository.existsById(supplier.getSupplier_id())) {
                //     return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                //             .body("Supplier is already associated with a product.");
                // }

                double product_baseprice = product.getProduct_baseprice();
                double product_supplier_price = supplierAssignmentDTO.getProductSupplierPrice();
                if (product_supplier_price > product_baseprice) {
                    ProductSupplier productSupplier = new ProductSupplier();
                    productSupplier.setProduct(product);
                    productSupplier.setSupplier(supplier);
                    productSupplier.setProduct_supplier_price(supplierAssignmentDTO.getProductSupplierPrice());
                    productSupplierRepository.save(productSupplier);
                }

            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Supplier Assigned Successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occured while assigning supplier");
        }

    }

    public ResponseEntity<ProductAssignmentDTO> getProductSuppliers(@PathVariable String productName) {
        try {

            Product product = productRepository.findByName(productName);

            if (product != null) {

                List<ProductSupplier> productSuppliers = productSupplierRepository
                        .findByProduct(product.getProduct_id());

                List<SupplierAssignmentDTO> supplierAssignments = new ArrayList<>();

                for (ProductSupplier productSupplier : productSuppliers) {
                    Supplier supplier = productSupplier.getSupplier();

                    if (supplier != null) {
                        SupplierAssignmentDTO supplierDTO = new SupplierAssignmentDTO();
                        supplierDTO.setSupplierName(supplier.getSupplier_name());
                        supplierDTO.setProductSupplierPrice(productSupplier.getProduct_supplier_price());
                        supplierAssignments.add(supplierDTO);
                    }
                }

                ProductAssignmentDTO responseDTO = new ProductAssignmentDTO();
                responseDTO.setProduct_name(product.getProduct_name());
                responseDTO.setSupplierAssignments(supplierAssignments);

                return ResponseEntity.ok(responseDTO);
            } else {

                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> updateSupplierAndPrice(String product_name,
            List<SupplierAssignmentDTO> supplierAssignments) {
        try {

            Product product = productRepository.findByName(product_name);

            if (product != null) {

                String firstSupplierName = supplierAssignments.get(0).getSupplierName();
                Supplier supplier = supplierRepository.findBySupplierName(firstSupplierName);

                if (supplier != null) {

                    List<ProductSupplier> productSuppliers = productSupplierRepository
                            .findByProduct(product.getProduct_id());
                    int count = 0;
                    while (count < supplierAssignments.size()) {
                        for (ProductSupplier productSupplier : productSuppliers) {

                            for (SupplierAssignmentDTO supplierAssignment : supplierAssignments) {
                                productSupplier.setSupplier(supplier);
                                productSupplier.setProduct_supplier_price(supplierAssignment.getProductSupplierPrice());
                                productSupplierRepository.save(productSupplier);
                            }
                            count++;

                        }
                    }

                } else {

                    return ResponseEntity.badRequest().body("Supplier not found.");
                }

                return ResponseEntity.noContent().build();
            } else {

                return ResponseEntity.badRequest().body("Product not found.");
            }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating supplier and price.");
        }
    }
}

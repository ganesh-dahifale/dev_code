package com.product_supplier.productsupplierservice.dto;

import java.util.List;



import lombok.Data;

@Data
public class ProductAssignmentDTO {
    private String product_name;
    private List<SupplierAssignmentDTO> supplierAssignments;
    
}

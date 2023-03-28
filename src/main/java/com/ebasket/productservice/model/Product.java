package com.ebasket.productservice.model;

import com.ebasket.productservice.dto.request.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private String category;
    private String subCategory;
    private List<String> productImages;
    private Map<String,String> quantityPriceMap;

}

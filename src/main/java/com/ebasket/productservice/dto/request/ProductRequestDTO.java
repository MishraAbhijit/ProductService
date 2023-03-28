package com.ebasket.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private String name;
    private String description;
    private String category;
    private String subCategory;
    private Map<String,String> quantityPriceMap;
}

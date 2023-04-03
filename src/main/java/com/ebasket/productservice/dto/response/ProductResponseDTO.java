package com.ebasket.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private String category;
    private String subCategory;
    private List<String> productImages;
    private Map<String,String> quantityPriceMap;
}

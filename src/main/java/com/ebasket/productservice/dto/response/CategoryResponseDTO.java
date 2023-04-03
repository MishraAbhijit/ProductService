package com.ebasket.productservice.dto.response;

import com.ebasket.productservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {
    private Map<String, List<ProductResponseDTO>> subCategoryResponse = new HashMap<>();
}

package com.ebasket.productservice.dto.response;

import com.ebasket.productservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesResponseDTO {
    private List<Category> categories;
}

package com.ebasket.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryRequestDTO {
    private String categoryName;
    private String subCategoryName;
}

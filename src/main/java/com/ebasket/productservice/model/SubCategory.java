package com.ebasket.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {
    private String name;
    private String imageURL;

    public SubCategory(String subCategoryName) {
        this.name=subCategoryName;
    }
}

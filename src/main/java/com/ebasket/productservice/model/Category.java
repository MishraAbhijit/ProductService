package com.ebasket.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("categories")
public class Category {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> subCategories = new ArrayList<>();
    private String imageURL;
}

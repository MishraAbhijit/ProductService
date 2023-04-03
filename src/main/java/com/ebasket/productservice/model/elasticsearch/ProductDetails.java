package com.ebasket.productservice.model.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "productId")
    private String productId;
    @Field(type = FieldType.Text, name = "productName")
    private String productName;
    @Field(type = FieldType.Text, name = "productDescription")
    private String productDescription;
    @Field(type = FieldType.Keyword, name = "category")
    private String category;
    @Field(type = FieldType.Keyword, name = "subCategory")
    private String subCategory;
}

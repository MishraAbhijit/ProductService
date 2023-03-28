package com.ebasket.productservice.repository;

import com.ebasket.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByCategory(String category);

    List<Product> findByCategoryAndSubCategory(String category, String subCategory);
}

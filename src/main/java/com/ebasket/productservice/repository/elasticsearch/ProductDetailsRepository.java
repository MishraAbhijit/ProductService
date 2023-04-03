package com.ebasket.productservice.repository.elasticsearch;

import com.ebasket.productservice.model.elasticsearch.ProductDetails;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends ElasticsearchRepository<ProductDetails,String> {
}

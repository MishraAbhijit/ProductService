package com.ebasket.productservice.service.elasticsearch;

import com.ebasket.productservice.model.elasticsearch.ProductDetails;
import com.ebasket.productservice.repository.elasticsearch.ProductDetailsRepository;
import com.ebasket.productservice.service.interfaces.ProductDetailsService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductDetailsServiceImpl implements ProductDetailsService {

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Override
    public ProductDetails saveProductDetails(ProductDetails productDetails) {
        productDetailsRepository.save(productDetails);
        return productDetails;
    }
}

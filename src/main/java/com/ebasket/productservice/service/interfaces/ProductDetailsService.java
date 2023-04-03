package com.ebasket.productservice.service.interfaces;

import com.ebasket.productservice.model.elasticsearch.ProductDetails;
import org.springframework.stereotype.Service;

@Service
public interface ProductDetailsService {
    ProductDetails saveProductDetails(ProductDetails productDetails);
}

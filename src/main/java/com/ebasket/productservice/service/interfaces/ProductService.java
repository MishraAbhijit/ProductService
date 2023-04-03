package com.ebasket.productservice.service.interfaces;

import com.ebasket.productservice.dto.request.ProductRequestDTO;
import com.ebasket.productservice.dto.response.ProductResponseDTO;
import com.ebasket.productservice.dto.response.ProductsResponseDTO;
import com.ebasket.productservice.dto.response.ResponseDTO;
import com.ebasket.productservice.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO fetchProductById(String productId);
    ProductsResponseDTO fetchProductsByCategory(String category);
    ProductsResponseDTO fetchProductsByCategoryAndSubCategory(String category,String subCategory);
    ProductsResponseDTO fetchAllProducts();
    ProductResponseDTO updateProduct(String productId,ProductRequestDTO productRequestDTO);
    ResponseDTO deleteProduct(String productId);

}

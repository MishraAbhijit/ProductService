package com.ebasket.productservice.service;

import com.ebasket.productservice.constants.Messages;
import com.ebasket.productservice.dto.request.ProductRequestDTO;
import com.ebasket.productservice.dto.response.CategoryResponseDTO;
import com.ebasket.productservice.dto.response.ProductsResponseDTO;
import com.ebasket.productservice.dto.response.ResponseDTO;
import com.ebasket.productservice.model.Product;
import com.ebasket.productservice.repository.ProductRepository;
import com.ebasket.productservice.service.interfaces.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(ProductRequestDTO productRequestDTO) {
        //For all the product images save it in S3.
        //Create Product Object
        Product product = new ObjectMapper().convertValue(productRequestDTO, Product.class);
        return productRepository.save(product);
    }

    @Override
    public Product fetchProductById(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.get();
    }

    @Override
    public ProductsResponseDTO fetchProductsByCategory(String category) {
        List<Product> productList = productRepository.findByCategory(category);
        return convertProductListToProductResponseDTO(productList);
    }

    @Override
    public ProductsResponseDTO fetchProductsByCategoryAndSubCategory(String category, String subCategory) {
        List<Product> productList = productRepository.findByCategoryAndSubCategory(category,subCategory);
        return convertProductListToProductResponseDTO(productList);
    }

    @Override
    public Product updateProduct(String productId, ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public ResponseDTO deleteProduct(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        productRepository.delete(product.get());
        return new ResponseDTO(HttpStatus.OK.value(), Messages.PRODUCT_DELETED_SUCCESSFULLY);
    }

    private ProductsResponseDTO convertProductListToProductResponseDTO(List<Product> productList){
        Map<String,CategoryResponseDTO> categoryResponse = new HashMap<>();

        productList.forEach(
                product -> {
                    if(categoryResponse.containsKey(product.getCategory())){
                        CategoryResponseDTO categoryResponseDTO = categoryResponse.get(product.getCategory());
                        if(categoryResponseDTO.getSubCategoryResponse().containsKey(product.getSubCategory())){
                            categoryResponseDTO.getSubCategoryResponse().get(product.getSubCategory()).add(product);
                        }else {
                            List<Product> list = new ArrayList<>();
                            list.add(product);
                            categoryResponseDTO.getSubCategoryResponse().put(product.getSubCategory(),list);
                        }
                    }else{
                        List<Product> list = new ArrayList<>();
                        list.add(product);
                        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
                        categoryResponseDTO.getSubCategoryResponse().put(product.getSubCategory(),list);
                        categoryResponse.put(product.getCategory(),categoryResponseDTO);
                    }
                }
        );

        return new ProductsResponseDTO(categoryResponse);
    }
}

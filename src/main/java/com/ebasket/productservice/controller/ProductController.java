package com.ebasket.productservice.controller;

import com.ebasket.productservice.dto.request.ProductRequestDTO;
import com.ebasket.productservice.dto.request.SubCategoryRequestDTO;
import com.ebasket.productservice.dto.response.ProductResponseDTO;
import com.ebasket.productservice.dto.response.ProductsResponseDTO;
import com.ebasket.productservice.dto.response.ResponseDTO;
import com.ebasket.productservice.model.Product;
import com.ebasket.productservice.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO requestDTO){
        ProductResponseDTO product = productService.addProduct(requestDTO);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/fetch")
    public ResponseEntity<ProductResponseDTO> findProductById(@RequestParam String productId){
        ProductResponseDTO product = productService.fetchProductById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/fetch/category")
    public ResponseEntity<ProductsResponseDTO> fetchProductsByCategory(@RequestParam String category){
        ProductsResponseDTO responseDTO = productService.fetchProductsByCategory(category);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/fetch/all")
    public ResponseEntity<ProductsResponseDTO> fetchProductsByCategory(){
        ProductsResponseDTO responseDTO = productService.fetchAllProducts();
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/fetch/all")
    public ResponseEntity<ProductsResponseDTO> fetchProductsByCategoryAndSubCategory(@RequestBody SubCategoryRequestDTO subCategoryRequestDTO){
        ProductsResponseDTO responseDTO = productService.fetchProductsByCategoryAndSubCategory(subCategoryRequestDTO.getCategoryName(), subCategoryRequestDTO.getSubCategoryName());
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestParam String productId,@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO product = productService.updateProduct(productId, productRequestDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteProduct(@RequestParam String productId){
        ResponseDTO responseDTO = productService.deleteProduct(productId);
        return ResponseEntity.ok(responseDTO);
    }
}

package com.ebasket.productservice.controller;

import com.ebasket.productservice.dto.request.CategoryRequestDTO;
import com.ebasket.productservice.dto.request.SubCategoryRequestDTO;
import com.ebasket.productservice.dto.response.CategoriesResponseDTO;
import com.ebasket.productservice.dto.response.ResponseDTO;
import com.ebasket.productservice.model.Category;
import com.ebasket.productservice.service.interfaces.CategoryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody CategoryRequestDTO requestDTO){
        Category category = categoryService.addCategory(requestDTO);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteCategory(@RequestParam String category){
        ResponseDTO responseDTO = categoryService.deleteCategory(category);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/upload/image")
    public ResponseEntity<Category> updateCategoryImage(@RequestParam String id,@RequestParam MultipartFile file){
        Category category = categoryService.updateCategoryImage(id, file);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/find")
    public ResponseEntity<Category> fetchCategoryById(@RequestParam String id){
        Category category = categoryService.fetchCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/find/name/{name}")
    public ResponseEntity<Category> fetchCategoryByName(@PathVariable String name){
        Category category = categoryService.fetchCategoryByName(name);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/all")
    public ResponseEntity<CategoriesResponseDTO> fetchAllCategories(){
        CategoriesResponseDTO categoriesResponseDTO = categoryService.fetchAllCategories();
        return ResponseEntity.ok(categoriesResponseDTO);
    }

    @PostMapping("/subcategory")
    public ResponseEntity<ResponseDTO> addSubCategory(@RequestBody SubCategoryRequestDTO subCategoryRequestDTO){
        ResponseDTO responseDTO = categoryService.addSubCategory(subCategoryRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/subcategory")
    public ResponseEntity<ResponseDTO> deleteSubCategory(@RequestBody SubCategoryRequestDTO subCategoryRequestDTO){
        ResponseDTO responseDTO = categoryService.deleteSubCategory(subCategoryRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}

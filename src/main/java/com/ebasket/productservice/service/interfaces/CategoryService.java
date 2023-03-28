package com.ebasket.productservice.service.interfaces;

import com.ebasket.productservice.dto.request.CategoryRequestDTO;
import com.ebasket.productservice.dto.request.SubCategoryRequestDTO;
import com.ebasket.productservice.dto.response.CategoriesResponseDTO;
import com.ebasket.productservice.dto.response.ResponseDTO;
import com.ebasket.productservice.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category addCategory(CategoryRequestDTO requestDTO);
    ResponseDTO deleteCategory(String category);
    Category updateCategoryImage();
    Category fetchCategoryById(String id);
    Category fetchCategoryByName(String name);
    CategoriesResponseDTO fetchAllCategories();
    ResponseDTO addSubCategory(SubCategoryRequestDTO subCategoryRequestDTO);
    ResponseDTO deleteSubCategory(SubCategoryRequestDTO subCategoryRequestDTO);


}

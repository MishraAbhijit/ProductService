package com.ebasket.productservice.service;

import com.ebasket.productservice.constants.Messages;
import com.ebasket.productservice.dto.request.CategoryRequestDTO;
import com.ebasket.productservice.dto.request.SubCategoryRequestDTO;
import com.ebasket.productservice.dto.response.CategoriesResponseDTO;
import com.ebasket.productservice.dto.response.ResponseDTO;
import com.ebasket.productservice.model.Category;
import com.ebasket.productservice.repository.CategoryRepository;
import com.ebasket.productservice.service.interfaces.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(CategoryRequestDTO requestDTO) {
        Category category = new ObjectMapper().convertValue(requestDTO, Category.class);
        return categoryRepository.save(category);
    }

    @Override
    public ResponseDTO deleteCategory(String category) {
        Optional<Category> optionalCategory = categoryRepository.findByName(category);

        categoryRepository.delete(optionalCategory.get());
        return new ResponseDTO(HttpStatus.OK.value(), Messages.CATEGORY_DELETED_SUCCESSFULLY);
    }

    @Override
    public Category updateCategoryImage() {
        return null;
    }

    @Override
    public Category fetchCategoryById(String id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.get();
    }

    @Override
    public Category fetchCategoryByName(String name) {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        return optionalCategory.get();
    }

    @Override
    public CategoriesResponseDTO fetchAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return new CategoriesResponseDTO(categoryList);
    }

    @Override
    public ResponseDTO addSubCategory(SubCategoryRequestDTO subCategoryRequestDTO) {
        Optional<Category> optionalCategory = categoryRepository.findByName(subCategoryRequestDTO.getCategoryName());
        optionalCategory.get().getSubCategories().add(subCategoryRequestDTO.getSubCategoryName());
        categoryRepository.save(optionalCategory.get());
        return new ResponseDTO(HttpStatus.OK.value(), Messages.SUB_CATEGORY_ADDED_SUCCESSFULLY);
    }

    @Override
    public ResponseDTO deleteSubCategory(SubCategoryRequestDTO subCategoryRequestDTO) {
        Optional<Category> optionalCategory = categoryRepository.findByName(subCategoryRequestDTO.getCategoryName());
        optionalCategory.get().getSubCategories().remove(subCategoryRequestDTO.getSubCategoryName());
        categoryRepository.save(optionalCategory.get());
        return new ResponseDTO(HttpStatus.OK.value(), Messages.SUB_CATEGORY_DELETED_SUCCESSFULLY);
    }
}

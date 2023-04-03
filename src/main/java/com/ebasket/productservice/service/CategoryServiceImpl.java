package com.ebasket.productservice.service;

import com.ebasket.productservice.constants.AppConstants;
import com.ebasket.productservice.constants.Messages;
import com.ebasket.productservice.dto.request.CategoryRequestDTO;
import com.ebasket.productservice.dto.request.SubCategoryRequestDTO;
import com.ebasket.productservice.dto.response.CategoriesResponseDTO;
import com.ebasket.productservice.dto.response.ResponseDTO;
import com.ebasket.productservice.model.Category;
import com.ebasket.productservice.model.SubCategory;
import com.ebasket.productservice.repository.CategoryRepository;
import com.ebasket.productservice.service.interfaces.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private S3ServiceImpl s3Service;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

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
    public Category updateCategoryImage(String id,MultipartFile multipartFile) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        String filePath = AppConstants.CATEGORIES_FILE_PATH+optionalCategory.get().getName()+"/"+ multipartFile.getOriginalFilename();
        try {
            s3Service.uploadImage(multipartFile,bucketName,filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

        optionalCategory.get().setImageURL(filePath);
        return categoryRepository.save(optionalCategory.get());
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
        categoryList.forEach(
                category -> {
                    category.setImageURL(s3Service.getPresignedUrl(bucketName,category.getImageURL(),120));
                }
        );
        return new CategoriesResponseDTO(categoryList);
    }

    @Override
    public ResponseDTO addSubCategory(SubCategoryRequestDTO subCategoryRequestDTO) {
        Optional<Category> optionalCategory = categoryRepository.findByName(subCategoryRequestDTO.getCategoryName());
        optionalCategory.get().getSubCategories().add(new SubCategory(subCategoryRequestDTO.getSubCategoryName()));
        categoryRepository.save(optionalCategory.get());
        return new ResponseDTO(HttpStatus.OK.value(), Messages.SUB_CATEGORY_ADDED_SUCCESSFULLY);
    }

    @Override
    public ResponseDTO deleteSubCategory(SubCategoryRequestDTO subCategoryRequestDTO) {
        Optional<Category> optionalCategory = categoryRepository.findByName(subCategoryRequestDTO.getCategoryName());
        optionalCategory.get().getSubCategories().forEach(
                subCategory -> {
                    if(subCategory.getName().equals(subCategoryRequestDTO)){
                        optionalCategory.get().getSubCategories().remove(subCategory);
                    }
                }
        );
        categoryRepository.save(optionalCategory.get());
        return new ResponseDTO(HttpStatus.OK.value(), Messages.SUB_CATEGORY_DELETED_SUCCESSFULLY);
    }
}

package com.jewellery.ecommerce.admin.category.service;


import com.jewellery.ecommerce.admin.category.model.CategoryDTO;
import com.jewellery.ecommerce.admin.category.model.CategoryRequest;
import com.jewellery.ecommerce.admin.material.model.MaterialDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO addCategory(CategoryRequest categoryRequest);

    CategoryDTO updateCategory(Integer id, CategoryRequest categoryRequest);

    CategoryDTO updateCategoryStatus(Integer id, String status);

    void deleteCategory(Integer id);

}
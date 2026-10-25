package com.jewellery.ecommerce.user.category.service.Impl;

import com.jewellery.ecommerce.entity.CategoryEntity;
import com.jewellery.ecommerce.user.category.model.CategoryDTO;
import com.jewellery.ecommerce.user.category.repository.CategoryRepository;
import com.jewellery.ecommerce.user.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userCategoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(@Qualifier("userCategoryRepository") CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAllPublicCategories() {
        return categoryRepository.findByStatusIgnoreCase("ACTIVE").stream()
                .map(entity -> new CategoryDTO(entity.getId(), entity.getCategoryName()))
                .collect(Collectors.toList());
    }
}

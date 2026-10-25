package com.jewellery.ecommerce.user.category.service;

import com.jewellery.ecommerce.user.category.model.CategoryDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAllPublicCategories();
}

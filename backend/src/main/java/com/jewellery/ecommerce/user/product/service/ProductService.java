package com.jewellery.ecommerce.user.product.service;

import com.jewellery.ecommerce.user.product.model.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findByCategoryId(Integer CategoryID);
    List<ProductDTO> findAllPublicProducts();
    ProductDTO findById(Integer id);
}

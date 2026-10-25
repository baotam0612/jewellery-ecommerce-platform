package com.jewellery.ecommerce.admin.product.service.Impl;

import com.jewellery.ecommerce.admin.order.repository.OrderRepository;
import com.jewellery.ecommerce.admin.product.Specification.ProductSpecification;
import com.jewellery.ecommerce.admin.product.model.dto.ProductDTO;
import com.jewellery.ecommerce.admin.product.model.request.ProductRequest;
import com.jewellery.ecommerce.admin.category.repository.CategoryRepository;
import com.jewellery.ecommerce.admin.product.repository.ProductRepository;
import com.jewellery.ecommerce.admin.product.service.ProductService;
import com.jewellery.ecommerce.entity.CategoryEntity;
import com.jewellery.ecommerce.entity.OrderEntity;
import com.jewellery.ecommerce.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@org.springframework.transaction.annotation.Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper,
            CategoryRepository categoryRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<ProductDTO> findAll(ProductRequest productRequest) {
        List<ProductEntity> li = productRepository.findAll(ProductSpecification.search(productRequest));
        List<ProductDTO> res = new ArrayList<>();
        for (ProductEntity it : li) {
            ProductDTO pd = modelMapper.map(it, ProductDTO.class);
            pd.setCategoryName(it.getCategory().getCategoryName());
            res.add(pd);
        }
        return res;
    }

    @Override
    public ProductDTO addProduct(ProductRequest productRequest) {
        ProductEntity res = new ProductEntity();
        applyRequest(res, productRequest);
        CategoryEntity category = categoryRepository.findByCategoryName(productRequest.getCategoryName());
        if (category == null)
            throw new RuntimeException("Category not be found");
        res.setCategory(category);
        productRepository.save(res);
        ProductDTO productDTO = toDTO(res);

        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(Integer id, ProductRequest productRequest) {
        ProductEntity productEntity = productRepository.findForUpdate(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
        CategoryEntity category = categoryRepository.findByCategoryName(productRequest.getCategoryName());
        if (category == null)
            throw new RuntimeException("Category not be found");
        applyRequest(productEntity, productRequest);
        productEntity.setCategory(category);
        productEntity = productRepository.save(productEntity);
        return toDTO(productEntity);
    }

    @Override
    public void deleteProduct(Integer id) {
        // 1. Kiểm tra sản phẩm tồn tại
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        // 2. Kiểm tra status product phải là "Unavailable"
        if (!("Unavailable".equalsIgnoreCase(productEntity.getStatus()))) {
            throw new RuntimeException("Product can only be deleted if status is 'Unavailable'!");
        }

        // 3. Tìm tất cả orders chứa product này
        List<OrderEntity> orders = orderRepository.findOrdersByProductId(id);

        if (!orders.isEmpty()) {
            throw new IllegalArgumentException("Không thể xóa sản phẩm đã có đơn hàng; hãy ngừng bán để giữ lịch sử");
        }

        // 5. Nếu tất cả điều kiện thỏa mãn, xóa product
        productRepository.deleteById(id);
    }

    private ProductDTO toDTO(ProductEntity product) {
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        dto.setCategoryName(product.getCategory().getCategoryName());
        return dto;
    }

    private void applyRequest(ProductEntity product, ProductRequest request) {
        product.setProductName(request.getProductName());
        product.setPrice(java.math.BigDecimal.valueOf(request.getPrice()));
        product.setStatus(request.getStatus());
        product.setImageUrl(request.getImageUrl());
        if (request.getQuantity() != null) product.setQuantity(request.getQuantity());
    }
}

package com.jewellery.ecommerce.admin.order.service;

import com.jewellery.ecommerce.admin.order.model.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDTO> findAll();
}

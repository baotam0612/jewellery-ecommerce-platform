package com.jewellery.ecommerce.admin.order.service;


import com.jewellery.ecommerce.admin.order.model.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {
    OrderDTO toDTO(com.jewellery.ecommerce.entity.OrderEntity order);
    List<OrderDTO>  findAll();
    OrderDTO updateOrderStatus(Integer orderId, String status);
    void deleteOrder(Integer orderId);
}

package com.jewellery.ecommerce.user.order.service;

import com.jewellery.ecommerce.user.order.model.dto.OrderRequest;

public interface UserOrderService {
    String placeOrder(OrderRequest request, String username);
}

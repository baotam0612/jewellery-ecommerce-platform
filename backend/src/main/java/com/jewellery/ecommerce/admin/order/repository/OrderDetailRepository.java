package com.jewellery.ecommerce.admin.order.repository;

import com.jewellery.ecommerce.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
}

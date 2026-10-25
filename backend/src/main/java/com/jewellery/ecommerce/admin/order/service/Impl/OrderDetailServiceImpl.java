package com.jewellery.ecommerce.admin.order.service.Impl;

import com.jewellery.ecommerce.admin.order.model.dto.OrderDTO;
import com.jewellery.ecommerce.admin.order.model.dto.OrderDetailDTO;
import com.jewellery.ecommerce.admin.order.repository.OrderDetailRepository;
import com.jewellery.ecommerce.admin.order.service.OrderDetailService;
import com.jewellery.ecommerce.entity.OrderDetailEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private ModelMapper modelMapper;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository, ModelMapper modelMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderDetailDTO> findAll() {
        List<OrderDetailEntity> orderDetailEntityList = orderDetailRepository.findAll();
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (OrderDetailEntity item : orderDetailEntityList) {
            OrderDetailDTO orderDetailDTO = modelMapper.map(item, OrderDetailDTO.class);
            orderDetailDTO.setProductName(item.getProduct().getProductName());
            orderDetailDTOList.add(orderDetailDTO);
        }
        return orderDetailDTOList;
    }
}

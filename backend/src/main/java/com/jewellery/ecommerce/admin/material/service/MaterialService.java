package com.jewellery.ecommerce.admin.material.service;


import com.jewellery.ecommerce.admin.material.model.MaterialDTO;
import com.jewellery.ecommerce.admin.material.model.MaterialRequest;
import com.jewellery.ecommerce.entity.MaterialEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MaterialService {
    List<MaterialDTO> findAll();

    List<MaterialDTO> findByName(String materialName);

    MaterialDTO findOneByName(String materialName);

    MaterialDTO CreateMaterial(MaterialRequest materialRequest);

    MaterialDTO UpdateMaterial(MaterialRequest materialRequest, Integer Id);

    void deleteMaterial(Integer id);
}

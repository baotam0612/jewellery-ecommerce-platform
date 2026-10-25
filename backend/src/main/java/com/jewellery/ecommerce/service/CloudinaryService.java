package com.jewellery.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

public interface CloudinaryService {
    Map uploadImage(MultipartFile file);
}

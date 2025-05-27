package com.example.session12.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemDTO {
    private Integer id;
    private String itemName;
    private Integer price;
    private String description;
    private MultipartFile imageFile; // For file upload
    private String image; // For existing image URL
    private String status;
    private String createdAt; // String for form input
}
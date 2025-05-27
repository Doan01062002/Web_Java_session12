package com.example.session12.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Integer id;

    @NotBlank(message = "Tên mặt hàng không được để trống")
    @Size(max = 150, message = "Tên mặt hàng không được vượt quá 150 ký tự")
    private String itemName;

    @Min(value = 0, message = "Giá không được âm")
    private Integer price;

    private String description;

    @Size(max = 200, message = "URL ảnh không được vượt quá 200 ký tự")
    private String image;

    @NotNull(message = "Trạng thái không được để trống")
    private String status;

    @NotNull(message = "Ngày tạo không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
}
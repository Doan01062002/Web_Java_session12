package com.example.session12.model;

import java.util.Date;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @NotBlank(message = "Mã sinh viên không được để trống")
    @Size(min = 5, max = 5, message = "Mã sinh viên phải có đúng 5 ký tự")
    private String studentId;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 200, message = "Họ tên không được vượt quá 200 ký tự")
    private String fullName;

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @Pattern(regexp = "^\\d{10,15}$", message = "Số điện thoại phải có từ 10 đến 15 chữ số")
    private String phoneNumber;

    private boolean gender;

    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Size(max = 255, message = "URL ảnh không được vượt quá 255 ký tự")
    private String profilePicture;

    @NotBlank(message = "Trạng thái không được để trống")
    private String accountStatus;
}
package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

// Các import cho Validator
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "username", length = 50)
    @NotNull(message = "Username không được để trống")
    @Size(min = 5, max = 20, message = "Username phải có từ 5 đến 20 ký tự") // Đã thêm dấu đóng ngoặc ")"
    private String username;
    
    @Column(name = "password", length = 50)
    @NotEmpty(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có tối thiểu 6 ký tự")
    private String password;
    
    @Column(name = "fullname", columnDefinition = "NVARCHAR(255)")
    @NotEmpty(message = "Họ tên không được để trống")
    private String fullname;
    
    @Column(name = "email", columnDefinition = "NVARCHAR(255)")
    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Địa chỉ email không hợp lệ")
    private String email;

    @Column(name = "roleid")
    @NotNull(message = "Role ID không được để trống")
    private int roleid;
}
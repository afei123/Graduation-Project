package com.example.demo.dto;

import lombok.Data;

/**
 * Created by AFei on 2018/3/11.
 */
@Data
public class LoginInfoDto {
    private long cityId;
    private long districtId;
    private String userName;
    private String phone;
    private String email;
    private String userRole;
    private String token;
    private String message;
}

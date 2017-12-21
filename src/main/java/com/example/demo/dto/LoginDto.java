package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by AFei on 2017/9/28.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String userName;
    private String password;
}

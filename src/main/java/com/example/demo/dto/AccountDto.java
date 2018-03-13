package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by AFei on 2017/9/29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private long id;
    private String name;
    private String districtName;    //小区名
    private double balance;         //余额
    private double userWater;       //用水量
    private int dayNum;
}
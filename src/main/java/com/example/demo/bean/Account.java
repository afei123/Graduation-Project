package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by AFei on 2017/9/28.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long cityId;
    private String name;
    private String phone;
    private String IDCardNumber;        //身份证号
    private long districtID;           //小区id
    private double balance;         //余额
    private double userWater;       //用水量
    private String email;
    private boolean validAccount = true;
    private boolean valid = true;
}

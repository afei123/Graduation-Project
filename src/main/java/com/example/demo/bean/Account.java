package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by AFei on 2017/9/28.
 */
@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long cityId;
    private String name;
    private String idNumber;        //身份证号
    private long district;           //小区id
    private double balance;         //余额
    private double userWater;       //用水量
    private String email;
    private boolean valid = true;
}

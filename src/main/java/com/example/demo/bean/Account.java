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
    private String waterMeterId;          //水表号
    private String phone;
    private String houseNumber;        //门牌号
    private long districtId;            //小区id
    private String waterType;           //用水类型
    private boolean validAccount = true;
    private boolean valid = true;
}

package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by AFei on 2017/9/28.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WaterBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long accountId;
    private long workerId;
    private long DistrictId;
    private long goldId;        //余额
    private long userWaterId;   //用水量
    private double spend;
    private Date createDate;
    private boolean valid = true;
}

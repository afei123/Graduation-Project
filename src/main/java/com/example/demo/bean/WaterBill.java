package com.example.demo.bean;

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
public class WaterBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String waterMeterId;
    private long workerId;
    private long districtId;
    private double gold;        //余额
    private long userWaterId;   //用水量
    private double spend;
    private Date createDate;
    private boolean valid = true;

    public WaterBill(String waterMeterId,long workerId,long districtId,double gold,long userWaterId,double spend,Date createDate) {
        this.waterMeterId = waterMeterId;
        this.workerId = workerId;
        this.districtId = districtId;
        this.gold = gold;
        this.userWaterId = userWaterId;
        this.spend = spend;
        this.createDate = createDate;
    }
}

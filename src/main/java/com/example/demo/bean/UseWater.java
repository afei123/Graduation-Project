package com.example.demo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by AFei on 2018/3/12.
 */
@Data
@Entity
@NoArgsConstructor
public class UseWater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String waterMeterId;
    private long districtId;
    private double meterTitle;
    private Date createDate;
    private boolean valid = true;

    public UseWater(String waterMeterId,long districtId,double meterTitle,Date createDate) {
        this.waterMeterId = waterMeterId;
        this.meterTitle = meterTitle;
        this.districtId = districtId;
        this.createDate = createDate;
    }
}

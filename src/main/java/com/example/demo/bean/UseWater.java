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
 * Created by AFei on 2018/3/12.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UseWater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private long accountId;
    private long DistrictId;
    private double meterTitle;
    private Date createDate;
    private boolean valid = true;
}

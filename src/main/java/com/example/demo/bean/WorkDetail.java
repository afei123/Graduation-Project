package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by AFei on 2018/3/12.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WorkDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private long workerId;
    private long districtId;
    private String Detail;
    private boolean isOk = false;
    private boolean valid = true;
}

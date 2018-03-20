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
public class WorkCalender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private long workerId;
    private long workDetailId;
    private long geographyId;
    private Date workDate;
    private String description;
    private boolean valid = true;
}

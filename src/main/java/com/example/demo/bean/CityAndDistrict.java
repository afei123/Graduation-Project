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
@NoArgsConstructor
@AllArgsConstructor
public class CityAndDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long parentId;
    private String name;
    private int level;
    private boolean valid = true;
}

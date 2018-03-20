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
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long geographyId;
    private String userName;
    private String passWord;
    private String phone;
    private String email;
    private String userRole;
    private boolean appUser = true;
    private boolean valid = true;
}

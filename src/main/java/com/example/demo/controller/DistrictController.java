package com.example.demo.controller;

import com.example.demo.bean.District;
import com.example.demo.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AFei on 2018/3/13.
 */
@RestController
@RequestMapping(value = "/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping("/insterDistrict")
    public ResponseEntity<Object> insterDistrict(@RequestBody District district){
        try {
            districtService.insterDistrict(district);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/updateDistrict")
    public ResponseEntity<Object> updateDistrict(@RequestBody District district){
        try {
            districtService.updateDistrict(district);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

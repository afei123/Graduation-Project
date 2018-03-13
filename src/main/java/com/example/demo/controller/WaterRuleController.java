package com.example.demo.controller;

import com.example.demo.bean.WaterRule;
import com.example.demo.service.WaterRuleService;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by AFei on 2018/3/13.
 */
@RestController
@RequestMapping(value = "/waterRule")
public class WaterRuleController {
    @Autowired
    private WaterRuleService waterRuleService;

    @PostMapping("/insterWaterRule")
    public ResponseEntity<Object> insterWaterRule(@RequestBody WaterRule waterRule){
        try {
            waterRuleService.insterWaterRule(waterRule);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/updateWaterRule")
    public ResponseEntity<Object> updateWaterRule(@RequestBody WaterRule waterRule){
        try {
            waterRuleService.updateWaterRule(waterRule);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/deleteWaterRule/{waterRuleId}")
    public ResponseEntity<Object> delteWaterRule(@PathVariable long waterRuleId){
        try {
            waterRuleService.deleteWaterRule(waterRuleId);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/searchWaterRule/{districtId}")
    public ResponseEntity<List<WaterRule>> searchWaterRule(@PathVariable long districtId){
        try {
            List<WaterRule> waterRuleList = waterRuleService.searchWaterRule(districtId);
            return new ResponseEntity<>(waterRuleList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

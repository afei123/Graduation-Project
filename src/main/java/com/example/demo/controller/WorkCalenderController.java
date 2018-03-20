package com.example.demo.controller;

import com.example.demo.bean.WorkCalender;
import com.example.demo.service.WorkCalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/workCalender")
public class WorkCalenderController {

    @Autowired
    private WorkCalenderService workCalenderService;

    public ResponseEntity<List<WorkCalender>> searchWorkCalender(Date date){
        int geographyId = 5;
        List<WorkCalender> workCalenderList;
        try {
            workCalenderList  = workCalenderService.searchWorkCalender(date,geographyId);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(workCalenderList,HttpStatus.OK);
    }
}

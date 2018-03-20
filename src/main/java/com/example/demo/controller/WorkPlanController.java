package com.example.demo.controller;

import com.example.demo.bean.WorkPlan;
import com.example.demo.dto.WorkPlanDto;
import com.example.demo.service.WorkPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by AFei on 2018/3/13.
 */
@RestController
@RequestMapping(value = "/workPlan")
public class WorkPlanController {
    @Autowired
    private WorkPlanService workPlanService;

    @PostMapping("/searchWorkPlan")
    public List<WorkPlanDto> searchWorkPlan(@RequestBody WorkPlan workPlan){
        return workPlanService.searchWorkPlan(workPlan);
    }
}

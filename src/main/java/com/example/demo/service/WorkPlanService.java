package com.example.demo.service;

import com.example.demo.bean.WorkPlan;
import com.example.demo.dto.WorkPlanDto;

import java.util.List;

/**
 * Created by AFei on 2018/3/13.
 */
public interface WorkPlanService {
    List<WorkPlanDto> searchWorkPlan(WorkPlan workPlan);
}

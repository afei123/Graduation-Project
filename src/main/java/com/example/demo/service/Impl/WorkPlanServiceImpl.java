package com.example.demo.service.Impl;

import com.example.demo.Specification.WorkPlanSpecification;
import com.example.demo.Specification.WorkerSpecification;
import com.example.demo.bean.District;
import com.example.demo.bean.WorkPlan;
import com.example.demo.bean.Worker;
import com.example.demo.dto.WorkPlanDto;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.WorkPlanRepository;
import com.example.demo.repository.WorkerRepository;
import com.example.demo.service.WorkPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkPlanServiceImpl implements WorkPlanService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WorkPlanRepository workPlanRepository;
    @Autowired
    private WorkPlanSpecification workPlanSpecification;
    @Override
    public List<WorkPlanDto> searchWorkPlan(WorkPlan workPlan) {
        Specification<WorkPlan> workPlanSpecification = this.workPlanSpecification.create(workPlan);
        List<WorkPlan> workPlanList = workPlanRepository.findAll(workPlanSpecification);
        List<WorkPlanDto> workPlanDtoList = new ArrayList<>();
        for (WorkPlan plan : workPlanList) {
            workPlanDtoList.add(getWorkPlanDto(plan));
        }
        return workPlanDtoList;
    }

    private WorkPlanDto getWorkPlanDto(WorkPlan plan) {
        WorkPlanDto workPlanDto = new WorkPlanDto();
        workPlanDto.setDetail(plan.getDetail());
        workPlanDto.setId(plan.getId());
        workPlanDto.setBeginDate(plan.getBeginDate());
        workPlanDto.setEndDate(plan.getEndDate());
        workPlanDto.setIsOk(plan.isOk() ? "是":"否");
        Worker worker = workerRepository.findOne(plan.getWorkerId());
        workPlanDto.setWorkerName(worker.getUserName());
        District district = districtRepository.findOne(plan.getDistrictId());
        workPlanDto.setDistrictName(district.getName());
        return workPlanDto;
    }
}

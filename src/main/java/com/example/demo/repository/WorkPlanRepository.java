package com.example.demo.repository;

import com.example.demo.bean.WorkPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by AFei on 2018/3/12.
 */
public interface WorkPlanRepository extends JpaRepository<WorkPlan,Long>,JpaSpecificationExecutor<WorkPlan> {
}

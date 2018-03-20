package com.example.demo.repository;

import com.example.demo.bean.UseWater;
import com.example.demo.bean.WaterBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AFei on 2018/3/12.
 */
public interface WaterBillRepository extends JpaRepository<WaterBill,Long> {
    List<WaterBill> findByWaterMeterIdAndValidOrderByCreateDate(String waterMeterId, boolean b);

}


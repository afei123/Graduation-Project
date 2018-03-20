package com.example.demo.repository;

import com.example.demo.bean.UseWater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AFei on 2018/3/12.
 */
public interface UseWaterRepository extends JpaRepository<UseWater,Long> {
    List<UseWater> findByWaterMeterIdAndValidOrderByCreateDate(String waterMeterId, boolean valid);
}

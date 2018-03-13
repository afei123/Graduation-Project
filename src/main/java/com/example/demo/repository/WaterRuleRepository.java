package com.example.demo.repository;

import com.example.demo.bean.WaterRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AFei on 2018/3/12.
 */
public interface WaterRuleRepository extends JpaRepository<WaterRule,Long> {

    List<WaterRule> findByTypeAndAreaIdAndIsCityAndValid(String type, long areaId, boolean city, boolean b);

    List<WaterRule> findByTypeAndAreaIdAndIsCityAndValidOrderByLevel(String type, long areaId, boolean city, boolean b);

    List<WaterRule> findByAreaIdAndIsCityAndValid(long cityId, boolean b, boolean b1);
}

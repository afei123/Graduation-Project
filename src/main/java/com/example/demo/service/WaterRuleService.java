package com.example.demo.service;

import com.example.demo.bean.WaterRule;

import java.util.List;

/**
 * Created by AFei on 2018/3/13.
 */
public interface WaterRuleService {
    void insterWaterRule(WaterRule waterRule);

    void updateWaterRule(WaterRule waterRule);

    void deleteWaterRule(long waterRuleId);

    List<WaterRule> searchWaterRule(long districtId);
}

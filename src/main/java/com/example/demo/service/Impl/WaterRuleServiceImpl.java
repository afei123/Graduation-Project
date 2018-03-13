package com.example.demo.service.Impl;

import com.example.demo.bean.District;
import com.example.demo.bean.WaterRule;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.WaterRuleRepository;
import com.example.demo.service.WaterRuleService;
import com.example.demo.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AFei on 2018/3/13.
 */
@Service
public class WaterRuleServiceImpl implements WaterRuleService {
    @Autowired
    private WaterRuleRepository waterRuleRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public void insterWaterRule(WaterRule waterRule) {
        List<WaterRule> waterRuleList = waterRuleRepository.findByTypeAndAreaIdAndIsCityAndValidOrderByLevel(waterRule.getType(),waterRule.getAreaId(),waterRule.isCity(),true);
        int waterRuleLevel = ListUtils.isNotBlankList(waterRuleList) ? waterRuleList.get(0).getLevel() : 0;
        waterRule.setLevel(++waterRuleLevel);
        waterRuleRepository.save(waterRule);
    }

    @Override
    public void updateWaterRule(WaterRule waterRule) {
        WaterRule one = waterRuleRepository.findOne(waterRule.getId());
        one.setMaxWater(waterRule.getMaxWater());
        one.setType(waterRule.getType());
        one.setUnitPrice(waterRule.getUnitPrice());
        one.setWorkId(waterRule.getWorkId());
    }

    @Override
    public void deleteWaterRule(long waterRuleId) {
        WaterRule one = waterRuleRepository.findOne(waterRuleId);
        List<WaterRule> waterRuleList = waterRuleRepository.findByTypeAndAreaIdAndIsCityAndValid(one.getType(), one.getAreaId(),one.isCity(), true);
        for (WaterRule waterRule : waterRuleList) {
            if(waterRule.getLevel() > one.getLevel()){
                waterRule.setLevel(waterRule.getLevel()-1);
                waterRuleRepository.save(waterRule);
            }
        }
    }

    @Override
    public List<WaterRule> searchWaterRule(long districtId) {
        List<WaterRule> waterRuleList;
        District one = districtRepository.findOne(districtId);
        if(one.isUseCityRule()) {
            waterRuleList = waterRuleRepository.findByAreaIdAndIsCityAndValid(one.getCityId(),true, true);
        }else {
            waterRuleList = waterRuleRepository.findByAreaIdAndIsCityAndValid(districtId, false ,true);
        }
        return waterRuleList;
    }
}

package com.example.demo.service.Impl;

import com.example.demo.bean.District;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AFei on 2018/3/13.
 */
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public void updateDistrict(District district) {
        District one = districtRepository.findOne(district.getId());
        one.setName(district.getName());
        one.setUseCityRule(district.isUseCityRule());
    }

    @Override
    public void insterDistrict(District district) {
        districtRepository.save(district);
    }
}

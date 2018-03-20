package com.example.demo.service;

import com.example.demo.dto.WaterBillDto;

import java.util.List;
import java.util.Map;

public interface WaterBillService {
    void countWaterBill(List<Map<String,Object>> useWaterList,long workerId) throws Exception;

    List<WaterBillDto> searchWaterBill(WaterBillDto waterBillDto);
}

package com.example.demo.controller;

import com.example.demo.dto.WaterBillDto;
import com.example.demo.service.WaterBillService;
import com.example.demo.utils.AnalysisExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/waterBill")
public class WaterBillController {
    @Autowired
    private WaterBillService waterBillService;

    @PostMapping("/searchWaterBill")
    public List<WaterBillDto> searchWaterBill(@RequestBody WaterBillDto waterBillDto){
        List<WaterBillDto> waterBillDtoList = waterBillService.searchWaterBill(waterBillDto);
        return waterBillDtoList;
    }
    @PostMapping("/countWaterBill")
    public ResponseEntity<Object> countWaterBill(@RequestParam("file")MultipartFile file){
        long workerId = 1;
        try {
            AnalysisExcel analysisExcel = new AnalysisExcel(file);
            List<Map<String, Object>> useWaterList = analysisExcel.getFirstSheet();
            waterBillService.countWaterBill(useWaterList,workerId);
        }catch(Exception e){
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("导入成功",HttpStatus.OK);
    }
}

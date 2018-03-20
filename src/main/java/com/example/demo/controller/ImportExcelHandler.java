package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/excelHandler")
public class ImportExcelHandler {
    @Autowired
    private WaterBillController waterBillController;

    @PostMapping("/excelHandler")
    public ResponseEntity<Object> excelHandler(@RequestParam("file")MultipartFile file, @RequestParam("select") String select){
        ResponseEntity<Object> obj = waterBillController.countWaterBill(file);
        return obj;
    }
}

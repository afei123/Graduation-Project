package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class WaterBillDto {
    private String phone;
    private String waterMeterId;
    private double spend;
    private double gold;
    private double useWater;
    private String workerName;
    private Date date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getDate(){
        return this.date;
    }
    public String getGold(){
        return String.format("%.2f",this.gold);
    }
    public String getSpend(){
        return String.format("%.2f",this.spend);
    }
}

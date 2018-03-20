package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WorkPlanDto {
    private long id;
    private String workerName;
    private String districtName;
    private String detail;
    private Date beginDate;
    private Date endDate;
    private String isOk;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getBeginDate(){
        return  this.beginDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEndDate(){
        return this.endDate;
    }
}

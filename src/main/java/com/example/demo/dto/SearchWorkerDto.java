package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by AFei on 2017/9/29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchWorkerDto {
    private String workerName;
    private long districtId;
    private Date workDate;
}

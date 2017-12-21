package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by AFei on 2017/9/28.
 */
@Data
@ToString
@AllArgsConstructor
public class ResponseDto {
    private int statusCode;
    private String description;

    public ResponseDto(){
        this.statusCode = 1;
        this.description = "success";
    }
}

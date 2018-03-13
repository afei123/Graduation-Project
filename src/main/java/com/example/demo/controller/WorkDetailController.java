package com.example.demo.controller;

import com.example.demo.service.WorkDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AFei on 2018/3/13.
 */
@RestController
@RequestMapping(value = "/workDetail")
public class WorkDetailController {
    @Autowired
    private WorkDetailService workDetailService;
}

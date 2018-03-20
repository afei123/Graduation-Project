package com.example.demo.service;

import com.example.demo.bean.WorkCalender;

import java.util.Date;
import java.util.List;

public interface WorkCalenderService {
    List<WorkCalender> searchWorkCalender(Date date, int geographyId);
}

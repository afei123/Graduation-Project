package com.example.demo.service.Impl;

import com.example.demo.bean.WorkCalender;
import com.example.demo.repository.WorkCalenderRepository;
import com.example.demo.service.WorkCalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkCalenderServiceImpl implements WorkCalenderService {
    @Autowired
    private WorkCalenderRepository workCalenderRepository;

    @Override
    public List<WorkCalender> searchWorkCalender(Date date, int geographyId) {
        List<WorkCalender> workCalenderList = workCalenderRepository.findByWorkDateAndGeographyIdAndValid(date,geographyId,true);
        return workCalenderList;
    }
}

package com.example.demo.repository;

import com.example.demo.bean.WorkCalender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by AFei on 2018/3/11.
 */
public interface WorkCalenderRepository extends JpaRepository<WorkCalender,Long> {
    List<WorkCalender> findByWorkDateAndValid(Date workDate, boolean b);
}

package com.example.demo.service;

import com.example.demo.dto.LoginDto;
import com.example.demo.bean.Worker;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.dto.SearchWorkerDto;

import java.util.List;

/**
 * Created by AFei on 2017/9/28.
 */
public interface WorkerService {
    LoginInfoDto login(LoginDto loginDto);

    void insertWorker(Worker worker);

    void updateWorker(Worker worker);

    void deleteWorker(long workId);

    List<Worker> searchWorker(SearchWorkerDto searchWorkerDto);
}

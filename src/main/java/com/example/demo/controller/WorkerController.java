package com.example.demo.controller;

import com.example.demo.bean.Worker;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.dto.SearchWorkerDto;
import com.example.demo.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by AFei on 2017/9/28.
 * 工作人员信息
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto){
        LoginInfoDto loginInfo = workerService.login(loginDto);
        return new ResponseEntity<>(loginInfo, HttpStatus.OK);
    }

    @PostMapping("/insertWorker")
    public ResponseEntity<Object> insertWorker(@RequestBody Worker worker){
        try {
            workerService.insertWorker(worker);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/updateWorker")
    public ResponseEntity<Object> updateWorker(@RequestBody Worker worker){
        try {
            workerService.updateWorker(worker);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/deleteWorker/{workId}")
    public ResponseEntity<Object> deleteWorker(@PathVariable long workId){
        try {
            workerService.deleteWorker(workId);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/searchWorker")
    public ResponseEntity<List<Worker>> searchWorker(@RequestBody SearchWorkerDto searchWorkerDto){
        try {

        List<Worker> workerList = workerService.searchWorker(searchWorkerDto);
        return new ResponseEntity<>(workerList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

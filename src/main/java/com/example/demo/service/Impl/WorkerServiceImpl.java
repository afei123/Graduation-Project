package com.example.demo.service.Impl;


import com.example.demo.Specification.WorkerSpecification;
import com.example.demo.bean.WorkCalender;
import com.example.demo.bean.Worker;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.LoginInfoDto;
import com.example.demo.dto.SearchWorkerDto;
import com.example.demo.repository.WorkCalenderRepository;
import com.example.demo.repository.WorkerRepository;
import com.example.demo.service.WorkerService;
import com.example.demo.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by AFei on 2017/9/28.
 */
@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private WorkCalenderRepository workCalenderRepository;
    @Autowired
    private WorkerSpecification workerSpecification;

    @Override
    public LoginInfoDto login(LoginDto loginDto) {
        List<Worker> WorkerList = workerRepository.findByUserNameAndPassWordAndValid(loginDto.getUserName(),loginDto.getPassword(),true);
        LoginInfoDto loginInfoDto = new LoginInfoDto();
        if(ListUtils.isNotBlankList(WorkerList) && WorkerList.size() == 1) {
            Worker worker = WorkerList.get(0);
            loginInfoDto.setUserName(worker.getUserName());
            loginInfoDto.setUserRole(worker.getUserRole());
        }else{
            loginInfoDto.setMessage("没有此用户");
        }
        return loginInfoDto;
    }

    @Override
    public void insertWorker(Worker worker) {
        workerRepository.save(worker);
    }

    @Override
    public void updateWorker(Worker worker) {
        Worker one = workerRepository.findOne(worker.getId());
        one.setPassWord(worker.getPassWord());
        one.setEmail(worker.getEmail());
        one.setUserRole(worker.getUserRole());
        one.setAppUser(worker.isAppUser());
        one.setPhone(worker.getPhone());
        workerRepository.save(one);
    }

    @Override
    public void deleteWorker(long workId) {
        Worker one = workerRepository.findOne(workId);
        one.setAppUser(false);
        workerRepository.save(one);
    }

    @Override
    public List<Worker> searchWorker(SearchWorkerDto searchWorkerDto) {
        Specification<Worker> Specification = workerSpecification.create(searchWorkerDto);
        List<Worker> workerList = workerRepository.findAll(Specification);
        if(searchWorkerDto.getWorkDate() != null){
            List<WorkCalender> workCalenderList = workCalenderRepository.findByWorkDateAndValid(searchWorkerDto.getWorkDate(), true);
            List<Long> workerIds = workCalenderList.stream().map(w -> w.getWorkerId()).collect(Collectors.toList());
            List<Worker> collect = workerList.stream().filter(worker -> workerIds.contains(worker.getId())).collect(Collectors.toList());
            workerList = collect;
        }
        return workerList;
    }


}

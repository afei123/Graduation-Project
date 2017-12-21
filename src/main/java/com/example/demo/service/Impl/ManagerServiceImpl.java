package com.example.demo.service.Impl;


import com.example.demo.bean.Manager;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.service.ManagerService;
import com.example.demo.utils.listUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AFei on 2017/9/28.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    @Override
    public ResponseDto login(LoginDto loginDto) {
        List<Manager> ManagerList = managerRepository.findByUserNameAndPassWordAndValid(loginDto.getUserName(),loginDto.getPassword(),true);
        if(listUtils.listNotNull(ManagerList)) {
            return new ResponseDto();
        }
        return new ResponseDto(0,"error");
    }

    @Override
    public ResponseDto insertAndUpdateManager(Manager manager) {
        managerRepository.save(manager);
        return new ResponseDto();
    }

    @Override
    public ResponseDto managerNotValid(long id , long cityId) {
        Manager NotvalidManager = managerRepository.findByIdAndCityIdAndValid(id, cityId, true);
        if(NotvalidManager != null){
            NotvalidManager.setValid(false);
            managerRepository.save(NotvalidManager);
            return new ResponseDto();
        }
        return new ResponseDto(0,"没有该工作人员");
    }


}

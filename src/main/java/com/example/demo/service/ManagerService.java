package com.example.demo.service;

import com.example.demo.bean.Manager;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.ResponseDto;

/**
 * Created by AFei on 2017/9/28.
 */
public interface ManagerService {
    ResponseDto login(LoginDto loginDto);

    ResponseDto insertAndUpdateManager(Manager manager);

    ResponseDto managerNotValid(long id, long cityId);
}

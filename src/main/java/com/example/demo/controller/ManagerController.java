package com.example.demo.controller;

import com.example.demo.bean.Manager;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by AFei on 2017/9/28.
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginDto loginDto){
        return managerService.login(loginDto);
    }

    @PostMapping("/insertAndUpdateManager")
    public ResponseDto insertAndUpdateManager(@RequestBody Manager manager){
        return managerService.insertAndUpdateManager(manager);
    }

    @PostMapping("/managerNotValid/{id}")
    public ResponseDto managerNotValid(@PathVariable long id){
        return managerService.managerNotValid(id,4);
    }

    public ResponseDto cookieTest(HttpServletResponse ss, HttpServletRequest Request){
        Cookie cookie = new Cookie("", "");
        cookie.setMaxAge(24*60*60);
        ss.addCookie(cookie);
        List<Cookie> collect = Arrays.stream(Request.getCookies()).filter(m -> m.getName().equals("")).collect(Collectors.toList());

        return null ;
    }
}

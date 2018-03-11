package com.example.demo.controller;

import com.example.demo.bean.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AFei on 2017/9/29.
 * 客户信息
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/insterAccount")
    public ResponseEntity<Object> insterAccount(@RequestBody Account account){
        try {
            accountService.insterAccount(account);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/updateAccount")
    public ResponseEntity<Object> updateAccount(@RequestBody Account account){
        try {
            accountService.updateAccount(account);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

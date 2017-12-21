package com.example.demo.controller;

import com.example.demo.bean.Account;
import com.example.demo.dto.AccountDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SearchAccountDto;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by AFei on 2017/9/29.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/insterAndUpdateAccount")
    public ResponseDto insterAndUpdateAccount(@RequestBody Account account){
       return accountService.insterAndUpdateAccount(account);
    }
    @GetMapping("/accountNotValid/{id}")
    public ResponseDto accountNotValid(@PathVariable long id ){
        return accountService.accountNotValid(id,(long) 3);
    }
    @PostMapping("/searchAccount")
    public List<AccountDto> searchAccount(@RequestBody SearchAccountDto searchAccountDto){
        List<AccountDto> accountList =  accountService.searchAccount(searchAccountDto);
        return null;
    }
}

package com.example.demo.service;

import com.example.demo.bean.Account;
import com.example.demo.dto.AccountDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SearchAccountDto;

import java.util.List;

/**
 * Created by AFei on 2017/9/29.
 */
public interface AccountService {

    ResponseDto insterAndUpdateAccount(Account account);

    ResponseDto accountNotValid(long id, long cityId);

    List<AccountDto> searchAccount(SearchAccountDto searchAccountDto);
}

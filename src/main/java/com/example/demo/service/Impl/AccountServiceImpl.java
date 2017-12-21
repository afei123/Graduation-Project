package com.example.demo.service.Impl;

import com.example.demo.bean.Account;
import com.example.demo.dto.AccountDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SearchAccountDto;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by AFei on 2017/9/29.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseDto insterAndUpdateAccount(Account account) {
        accountRepository.save(account);
        return new ResponseDto();
    }

    @Override
    public ResponseDto accountNotValid(long id, long cityId) {
        Account account = accountRepository.findByIdAndCityIdAndValid(id,cityId,true);
        if(account != null) {
            account.setValid(false);
            accountRepository.save(account);
            return new ResponseDto();
        }
        return new ResponseDto(0,"没有该用户");
    }

    @Override
    public List<AccountDto> searchAccount(SearchAccountDto searchAccountDto) {
        return null;
    }


}

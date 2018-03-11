package com.example.demo.service.Impl;

import com.example.demo.bean.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AFei on 2017/9/29.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void insterAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        Account one = accountRepository.findOne(account.getId());
        one.setPhone(account.getPhone());
        one.setDistrictID(account.getDistrictID());
    }


}

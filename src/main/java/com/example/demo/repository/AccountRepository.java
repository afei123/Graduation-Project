package com.example.demo.repository;

import com.example.demo.bean.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by AFei on 2017/9/29.
 */
public interface AccountRepository extends JpaRepository<Account,Long>,JpaSpecificationExecutor<Account> {

    Account findByWaterMeterIdAndValid(String waterMeterId,boolean valid);
}

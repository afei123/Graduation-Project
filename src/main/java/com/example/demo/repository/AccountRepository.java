package com.example.demo.repository;

import com.example.demo.bean.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AFei on 2017/9/29.
 */
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByIdAndCityIdAndValid(long id, long cityId, boolean b);
}

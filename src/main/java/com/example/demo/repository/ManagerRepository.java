package com.example.demo.repository;

import com.example.demo.bean.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AFei on 2017/9/28.
 */
public interface ManagerRepository extends JpaRepository<Manager,Long> {

    List<Manager> findByUserNameAndPassWordAndValid(String userName, String password, boolean valid);

    Manager findByIdAndCityIdAndValid(long id, long cityId, boolean b);
}

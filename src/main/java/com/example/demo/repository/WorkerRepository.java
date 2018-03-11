package com.example.demo.repository;

import com.example.demo.bean.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by AFei on 2017/9/28.
 */
public interface WorkerRepository extends JpaRepository<Worker,Long>,JpaSpecificationExecutor<Worker> {

    List<Worker> findByUserNameAndPassWordAndValid(String userName, String password, boolean valid);

}

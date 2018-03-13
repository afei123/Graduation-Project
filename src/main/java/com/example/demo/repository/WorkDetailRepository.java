package com.example.demo.repository;

import com.example.demo.bean.WorkDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AFei on 2018/3/12.
 */
public interface WorkDetailRepository extends JpaRepository<WorkDetail,Long> {
}

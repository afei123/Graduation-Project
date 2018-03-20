package com.example.demo.Specification;

import com.example.demo.bean.Worker;
import com.example.demo.dto.SearchWorkerDto;
import com.example.demo.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AFei on 2018/3/11.
 */
@Service
public class WorkerSpecification  {

    public Specification<Worker> create(SearchWorkerDto searchWorkerDto){
      return new Specification<Worker>() {
          @Override
          public Predicate toPredicate(Root<Worker> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
              Expression<String> userName = root.get("userName").as(String.class);
              Expression<Long> districtId = root.get("districtId").as(long.class);
              List<Predicate> predicateList = new ArrayList<>();
              if(StringUtils.isNotBlank(searchWorkerDto.getWorkerName())){
                  Predicate likeName = cb.like(userName, "%" + searchWorkerDto.getWorkerName() + "%");
                  predicateList.add(likeName);
              }
              Predicate[] predicates = predicateList.toArray(new Predicate[predicateList.size()]);
              Predicate and = cb.and(predicates);
              query.where(and);
              return query.getRestriction();
          }
      };
    }
}

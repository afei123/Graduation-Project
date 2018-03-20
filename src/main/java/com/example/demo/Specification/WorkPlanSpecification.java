package com.example.demo.Specification;

import com.example.demo.bean.WorkPlan;
import com.example.demo.utils.NumUtils;
import com.example.demo.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;

@Service
public class WorkPlanSpecification {
    public Specification<WorkPlan> create(WorkPlan workPlan){
        return new Specification<WorkPlan>() {
            @Override
            public Predicate toPredicate(Root<WorkPlan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                ArrayList<Predicate> predicates = new ArrayList<>();
                if (NumUtils.isValidLong(workPlan.getWorkerId())) {
                    Expression<Long> waterMeterId = root.get("workId").as(long.class);
                    Predicate eqwaterMeter = cb.equal(waterMeterId, workPlan.getWorkerId());
                    predicates.add(eqwaterMeter);
                }
                if(NumUtils.isValidLong(workPlan.getDistrictId())){
                    Expression<Long> phone = root.get("districtId").as(long.class);
                    Predicate eqPhone = cb.equal(phone, workPlan.getDistrictId());
                    predicates.add(eqPhone);
                }
                Expression<Boolean> isOk = root.get("isOk").as(boolean.class);
                Predicate eqIsOk = cb.equal(isOk, workPlan.isOk());
                predicates.add(eqIsOk);
                Predicate[] predicateArr = predicates.toArray(new Predicate[predicates.size()]);
                Predicate and = cb.and(predicateArr);
                query.where(and);
                return query.getRestriction();
            }
        };
    }
}

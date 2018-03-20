package com.example.demo.Specification;

import com.example.demo.bean.Account;
import com.example.demo.dto.WaterBillDto;
import com.example.demo.utils.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;

@Service
public class AccountSpecification {
    public Specification<Account> create(WaterBillDto waterBillDto){
        return new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                ArrayList<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(waterBillDto.getWaterMeterId())) {
                    Expression<String> waterMeterId = root.get("waterMeterId").as(String.class);
                    Predicate eqwaterMeter = cb.equal(waterMeterId, waterBillDto.getWaterMeterId());
                    predicates.add(eqwaterMeter);
                }
                if(StringUtils.isNotBlank(waterBillDto.getPhone())){
                    Expression<String> phone = root.get("phone").as(String.class);
                    Predicate eqPhone = cb.like(phone, "%"+waterBillDto.getPhone()+"%");
                    predicates.add(eqPhone);
                }
                Predicate[] predicateArr = predicates.toArray(new Predicate[predicates.size()]);
                Predicate and = cb.and(predicateArr);
                query.where(and);
                return query.getRestriction();
            }
        };
    }
}

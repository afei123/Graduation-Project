package com.example.demo.service.Impl;

import com.example.demo.Specification.AccountSpecification;
import com.example.demo.bean.*;
import com.example.demo.dto.WaterBillDto;
import com.example.demo.repository.*;
import com.example.demo.service.WaterBillService;
import com.example.demo.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WaterBillServiceImpl implements WaterBillService {
    public static final String waterMeter = "水表号";
    public static final String numOfWater = "当前水表度数";

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private WaterBillRepository waterBillRepository;
    @Autowired
    private WaterRuleRepository waterRuleRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private UseWaterRepository useWaterRepository;
    @Autowired
    private AccountSpecification accountSpecification;

    @Override
    public void countWaterBill(List<Map<String, Object>> useWaterList,long workerId) throws Exception{
        for (Map<String, Object> useWater : useWaterList) {
            String waterMeterId = (String) useWater.get(waterMeter);
            double num = (double) useWater.get(numOfWater);
            Account account = getAccount(waterMeterId);
            double spend = countSpend(account,num);
            double gold = countGold(waterMeterId,spend);
            long useWaterId = insertUseWater(account, num);
            WaterBill waterBill = new WaterBill(account.getWaterMeterId(), workerId, account.getDistrictId(), gold, useWaterId, spend, new Date());
            waterBillRepository.save(waterBill);
        }
    }

    @Override
    public List<WaterBillDto> searchWaterBill(WaterBillDto waterBillDto) {
        ArrayList<WaterBillDto> waterBillDtoList = new ArrayList<>();
        Specification<Account> accountSpecification = this.accountSpecification.create(waterBillDto);
        List<Account> accountList = accountRepository.findAll(accountSpecification);
        if(ListUtils.isNotBlankList(accountList)) {
            List<WaterBill> waterBillList = waterBillRepository.findByWaterMeterIdAndValidOrderByCreateDate(accountList.get(0).getWaterMeterId(), true);
            for (WaterBill waterBill : waterBillList) {
                waterBillDtoList.add(getWaterBillDto(waterBill, accountList.get(0)));
            }
        };
        return waterBillDtoList;
    }

    private WaterBillDto getWaterBillDto(WaterBill waterBill,Account account) {
        WaterBillDto waterBillDto = new WaterBillDto();
        waterBillDto.setGold(waterBill.getGold());
        waterBillDto.setPhone(account.getPhone());
        waterBillDto.setSpend(waterBill.getSpend());
        waterBillDto.setWaterMeterId(account.getWaterMeterId());
        UseWater useWater = useWaterRepository.findOne(waterBill.getId());
        waterBillDto.setUseWater(useWater.getMeterTitle());
        waterBillDto.setDate(useWater.getCreateDate());
        Worker worker = workerRepository.findOne(waterBill.getWorkerId());
        waterBillDto.setWorkerName(worker.getUserName());
        return waterBillDto;
    }

    private double countSpend(Account account,double num) {
        List<UseWater> accountUseWaterHistorys = useWaterRepository.findByWaterMeterIdAndValidOrderByCreateDate(account.getWaterMeterId(),true);
        District one = districtRepository.findOne(account.getDistrictId());
        boolean isUseCityRule = one.isUseCityRule();
        List<WaterRule> waterRuleList = waterRuleRepository.findByTypeAndAreaIdAndIsCityAndValidOrderByLevel(account.getWaterType(),isUseCityRule ? one.getGeographyId():one.getId(), isUseCityRule, true);
        UseWater lastUseWater = accountUseWaterHistorys.get(accountUseWaterHistorys.size() - 1);
        double useWaterNum = num - lastUseWater.getMeterTitle();
        double spend = 0.0;
        WaterRule lastWaterRule = waterRuleList.get(waterRuleList.size() - 1);
        double maxWater = lastWaterRule.getMaxWater();
        if(useWaterNum > maxWater){
            spend = lastWaterRule.getUnitPrice()* (spend - lastWaterRule.getMaxWater());
        }
        for (WaterRule waterRule : waterRuleList) {
            if(useWaterNum > waterRule.getMoreThenUpperLevel()){
                spend += waterRule.getMoreThenUpperLevel() * waterRule.getUnitPrice();
                useWaterNum -= waterRule.getMoreThenUpperLevel();
            }else{
                spend += useWaterNum * waterRule.getUnitPrice();
            }
        }
        return spend;
    }

    private double countGold(String waterMeterId, double spend) {
        List<WaterBill> waterBillList = waterBillRepository.findByWaterMeterIdAndValidOrderByCreateDate(waterMeterId,true);
        double gold = 0;
        if(ListUtils.isNotBlankList(waterBillList)){
            gold = waterBillList.get(waterBillList.size() - 1).getGold();
        }
        gold -= spend;
        return gold;
    }

    private Account getAccount(String waterMeterId)throws Exception{
        Account account = accountRepository.findByWaterMeterIdAndValid(waterMeterId,true);
        if(Objects.isNull(account)){
            throw new Exception("没有该水表号"+ waterMeterId);
        }
        return account;
    }
    private long insertUseWater(Account account,double meterTitle){
        UseWater useWater = new UseWater(account.getWaterMeterId(), account.getDistrictId(),meterTitle, new Date());
        UseWater save = useWaterRepository.save(useWater);
        return save.getId();
    }
}

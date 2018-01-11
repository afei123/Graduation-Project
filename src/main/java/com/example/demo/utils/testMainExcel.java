package com.example.demo.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by AFei on 2018/1/11.
 */
public class testMainExcel {
    public static void main(String[] args)throws Exception{
        long l1 = System.currentTimeMillis();
        AnalysisExcel analysisExcel = new AnalysisExcel("C:\\Users\\AFei\\Desktop\\省市区.xlsx");
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);
        List<Map<String, String>> sheet = analysisExcel.getSheet(0);
        long l3 = System.currentTimeMillis();
        System.out.println(l3-l2);
        List excelInformation = analysisExcel.getExcelInformation();
        long l4 = System.currentTimeMillis();
        System.out.println(l4-l3);
        List<Map<String, String>> sheetd = analysisExcel.getSheet(0);
        long l5 = System.currentTimeMillis();
        System.out.println(l5-l4);

    }
}

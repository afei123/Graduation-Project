package com.example.demo.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AFei on 2018/1/11.
 */
public class AnalysisExcel {
    private Workbook sheets;
    private List<Integer> headRowList = new ArrayList<>();
    private List<Class> classList = new ArrayList<>();

    public AnalysisExcel(String filePath) throws Exception{
        sheets = WorkbookFactory.create(new File(filePath));
    }
    public void setHeadRowList(List<Integer> headRowList){
        if(classList.size() > 0 && headRowList.size() != classList.size()){
            return ;
        }
        this.headRowList = headRowList;
    }
    public void setClassList(List<Class> classList){
        if(headRowList.size() > 0 && headRowList.size() != classList.size()){
            return ;
        }
        this.classList = classList;
    }
    /**
     * 获取整个excel信息
     * @return
     * @throws Exception
     */
    public List getExcelInformation()throws Exception{
        ArrayList<List> lists = new ArrayList<>();
        for (int i = 0; i < sheets.getNumberOfSheets(); i++) {
            List sheet = chooseSheetMethod(sheets , i);
            lists.add(sheet);
        }
        return lists;
    }

    public List chooseSheetMethod(Workbook sheets,int sheetNum)throws Exception{
        List sheet = null;
        if (classList.size() > 0 && headRowList.size() > 0){
            sheet = getSheet(sheetNum, classList.get(sheetNum), headRowList.get(sheetNum));
        }else{
            sheet = getSheet(sheetNum);
        }
        return sheet;
    }
    /**
     * 获取excel页信息
     * @param sheetNum
     * @return
     * @throws Exception
     */
    public List<Map<String,String>> getSheet(int sheetNum )throws Exception{
        return getSheet(sheetNum , 0);
    }
    public List<Map<String,String>> getSheet(int sheetNum,int headRow)throws Exception{
        return getSheet(sheets.getSheetAt(sheetNum),headRow);
    }
    public List<Map<String,String>> getSheet(Sheet sheet , int headRow){
        ArrayList<Map<String,String>> sheetInformation = new ArrayList<>();
        if(!checkSheetIsValid(sheet)){
            return sheetInformation;
        };
        List<String> excelHead = getExcelHead(sheet , headRow);
        for (++headRow ; headRow <= sheet.getLastRowNum() ; headRow++){
            Row row = sheet.getRow(headRow);
            Map<String,String> objMap = setObjByRow(row,excelHead);
            sheetInformation.add(objMap);
        }
        return sheetInformation;
    }
    public <T> List<T> getSheet(int sheetNum , Class<T> clazz)throws Exception{
        return getSheet(sheetNum,clazz,0);
    }
    public <T> List<T> getSheet(int sheetNum , Class<T> clazz ,int headRow)throws Exception{
        return getSheet(sheets.getSheetAt(sheetNum),clazz,headRow);
    }
    public <T> List<T> getSheet(Sheet sheet , Class<T> clazz , int headRow) throws Exception {
        ArrayList<T> sheetInformation = new ArrayList<>();
        if(!checkSheetIsValid(sheet)){
            return sheetInformation;
        };
        List<String> excelHead = getExcelHead(sheet , headRow);
        for (++headRow ; headRow <= sheet.getLastRowNum() ;headRow++){
            Row row = sheet.getRow(headRow);
            T tObj = setObjByRow(row, clazz, excelHead);
            sheetInformation.add(tObj);
        }
        return sheetInformation;
    }

    /**
     * 封装行对象
     * @param row
     * @param clazz
     * @param excelHead
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T setObjByRow(Row row,Class<T> clazz ,List<String> excelHead)throws Exception{
        T tObj = clazz.newInstance();
        for (int i = 0 ; i < row.getLastCellNum(); i++){
            String field = excelHead.get(i);
            try {
                tObj = (T) clazz.getMethod("set" + field.replace(field.charAt(0), (char) (field.charAt(0) + 32)), String.class).invoke(tObj, toString(row.getCell(i)));
            }catch(Exception e){
                System.out.println("没有这个属性");
            }
        }
        return tObj;
    }
    public Map<String,String> setObjByRow(Row row,List<String> excelHead){
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0 ; i < row.getLastCellNum() ; i++){
            map.put(excelHead.get(i), toString(row.getCell(i)));
        }
        return map;
    }
    public List<String> getExcelHead(Sheet sheet , int headRow){
        ArrayList<String> headList = new ArrayList<>();
        Row row = sheet.getRow(headRow);
        for (int i = 0 ; i < row.getLastCellNum() ; i++){
            Cell cell = row.getCell(i);
            headList.add(cell.toString());
        }
        return headList;
    }
    public boolean checkSheetIsValid(Sheet sheet){
        if (sheet.getLastRowNum() > 0){
            return true;
        }
        return false;
    };
    public String toString(Cell cell){
        return cell == null ? "" : cell.toString();
    }
}

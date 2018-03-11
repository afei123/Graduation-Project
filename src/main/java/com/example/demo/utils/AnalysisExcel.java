package com.example.demo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AFei on 2018/1/11.
 */
public class AnalysisExcel {
    private Workbook sheets;
    private List<Integer> headRowList = new ArrayList<>();
    private List<Class> classList = new ArrayList<>();
    private int headNum;
    private static final Pattern[] NumPattern;
    private static final int isNum = 0;
    private static final int isDouble = 1;
    private static final int isLong = 2;

    static {
        NumPattern = new Pattern[]{Pattern.compile("^[\\-|0-9]{0,9}[\\.]{0,1}[0-9]{1,9}$"), Pattern.compile("^[\\-|0-9]{1,9}\\.[0-9]{1,9}$"), Pattern.compile(".*[id|Id|ID]$")};
    }

    public AnalysisExcel(String filePath) throws Exception {
        sheets = WorkbookFactory.create(new File(filePath));
    }

    public AnalysisExcel(MultipartFile file) throws Exception {
        sheets = new XSSFWorkbook(file.getInputStream());
    }

    public void setHeadRowList(Integer... headRows) {
        this.headRowList = Arrays.asList(headRows);
    }

    public void setClassList(Class... clazzs) {
        this.classList = Arrays.asList(clazzs);
    }

    /**
     * 获取整个excel信息
     *
     * @return
     * @throws Exception
     */
    public List getExcelInformation() throws Exception {
        ArrayList<List> ExcelInformation = new ArrayList<>();
        for (int i = 0; i < sheets.getNumberOfSheets(); i++) {
            List sheetInformation = chooseSheetMethod(i);
            ExcelInformation.add(sheetInformation);
        }
        return ExcelInformation;
    }

    private List chooseSheetMethod(int sheetNum) throws Exception {
        List sheetInformation;
        Class clazz = getObjByIndex(classList, sheetNum);
        Integer headNum = getObjByIndex(headRowList, sheetNum);
        if (headNum != null && clazz != null) {
            sheetInformation = getSheet(sheetNum, clazz, headNum);
        } else if (headNum != null) {
            sheetInformation = getSheet(sheetNum, headNum);
        } else if (clazz != null) {
            sheetInformation = getSheet(sheetNum, clazz);
        } else {
            sheetInformation = getSheet(sheetNum);
        }
        return sheetInformation;
    }

    /**
     * 获取Excel首页信息
     *
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getFirstSheet() throws Exception {
        return getSheet(0, 0);
    }

    public <T> List<T> getFirstSheet(Class<T> clazz) throws Exception {
        return getSheet(0, clazz, 0);
    }

    /**
     * 获取Excel指定页信息
     *
     * @param sheetNum
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> getSheet(int sheetNum) throws Exception {
        return getSheet(sheetNum, 0);
    }

    public List<Map<String, Object>> getSheet(int sheetNum, int headRow) throws Exception {
        return getSheet(sheets.getSheetAt(sheetNum), headRow);
    }

    public List<Map<String, Object>> getSheet(Sheet sheet, int headRow) {
        ArrayList<Map<String, Object>> sheetInformation = new ArrayList<>();
        if (!checkSheetIsValid(sheet)) {
            return sheetInformation;
        }
        ;
        List<String> excelHead = getExcelHead(sheet, headRow);
        for (++headNum; headNum <= sheet.getLastRowNum(); headNum++) {
            Row row = sheet.getRow(headNum);
            if(null != row && ListUtils.isNotBlankList(excelHead)) {
                Map<String, Object> objMap = setObjByRow(row, excelHead);
                sheetInformation.add(objMap);
            }
        }
        return sheetInformation;
    }

    public <T> List<T> getSheet(int sheetNum, Class<T> clazz) throws Exception {
        return getSheet(sheetNum, clazz, 0);
    }

    public <T> List<T> getSheet(int sheetNum, Class<T> clazz, int headRow) throws Exception {
        return getSheet(sheets.getSheetAt(sheetNum), clazz, headRow);
    }

    public <T> List<T> getSheet(Sheet sheet, Class<T> clazz, int headRow) throws Exception {
        ArrayList<T> sheetInformation = new ArrayList<>();
        if (!checkSheetIsValid(sheet)) {
            return sheetInformation;
        }
        ;
        List<String> excelHead = getExcelHead(sheet, headRow);
        for (++headNum; headNum <= sheet.getLastRowNum(); headNum++) {
            Row row = sheet.getRow(headNum);
            T tObj = setObjByRow(row, clazz, excelHead);
            sheetInformation.add(tObj);
        }
        return sheetInformation;
    }

    /**
     * 封装行对象
     *
     * @param row
     * @param clazz
     * @param excelHead
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T> T setObjByRow(Row row, Class<T> clazz, List<String> excelHead) throws Exception {
        T tObj = clazz.newInstance();
        for (int i = 0; i < row.getLastCellNum(); i++) {
            String field = excelHead.get(i);
            try {
                tObj = (T) clazz.getMethod("set" + field.replace(field.charAt(0), (char) (field.charAt(0) + 32)), toString(row.getCell(i), field).getClass()).invoke(tObj, toString(row.getCell(i), field));
            } catch (Exception e) {
                System.out.println("Boolean类型请自行书写set方法");
            }
        }
        return tObj;
    }

    private Map<String, Object> setObjByRow(Row row, List<String> excelHead) {
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < row.getLastCellNum(); i++) {
            map.put(excelHead.get(i), toString(row.getCell(i), excelHead.get(i)));
        }
        return map;
    }

    private List<String> getExcelHead(Sheet sheet, int headRow) {
        ArrayList<String> headList = new ArrayList<>();
        boolean isNotHeadRow = true;
        Row row = null;
        while (isNotHeadRow) {
            row = sheet.getRow(headRow);
            if (row != null) {
                isNotHeadRow = false;
                headNum = headRow;
            } else {
                headRow++;
            }
        }
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            headList.add(cell.toString());
        }
        return headList;
    }

    private boolean checkSheetIsValid(Sheet sheet) {
        if (sheet.getLastRowNum() > 0) {
            return true;
        }
        return false;
    }

    private <T> T getObjByIndex(List<T> list, int index) {
        if (list.size() - 1 < index) {
            return null;
        }
        return list.get(index);
    }

    private Object toString(Cell cell, String field) {
        if (cell == null) {
            return null;
        }
        int cellType = cell.getCellType();
        if (cellType == CellType.FORMULA.getCode()) {
            return cell.getDateCellValue();
        } else if (cellType == CellType.BOOLEAN.getCode()) {
            return cell.getBooleanCellValue();
        } else if (cellType == CellType.NUMERIC.getCode()) {
            if(DateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return formatter.format(date);
            }
            String num = cell.toString();
            if (checkNumClass(num, isNum)) {
                if (checkNumClass(field, isLong)) {
                    return Long.parseLong(num);
                } else if (checkNumClass(num, isDouble)) {
                    return Double.parseDouble(num);
                }
                return Integer.parseInt(num);
            }
            return num;
        } else if (cellType == CellType.BLANK.getCode()) {
            return null;
        } else {
            return cell.toString();
        }
    }

    private boolean checkNumClass(String check, int index) {
        Matcher matcher = NumPattern[index].matcher(check);
        boolean flag = matcher.matches();
        return flag;
    }
}

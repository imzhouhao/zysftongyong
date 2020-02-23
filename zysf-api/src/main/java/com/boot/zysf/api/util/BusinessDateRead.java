package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.service.BusinessDataService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class BusinessDateRead {
    @Autowired
    BusinessDataService businessDataService;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public BusinessDateRead() {
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public List<BusinessData> getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<BusinessData> businessDataList=new ArrayList<BusinessData>();
        try{
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            //System.out.println(isExcel2003);

           businessDataList= creatExcel(mFile.getInputStream(),isExcel2003);
        }catch (Exception e){
            e.printStackTrace();
        }
    return businessDataList;
    }
    public BusinessData getByName(String name){
        BusinessData businessData = new BusinessData().setEmpName(name);
        QueryWrapper<BusinessData> query = new QueryWrapper<>(businessData);
        BusinessData one = businessDataService.getOne(query);
        return one;
    }
    public List<BusinessData> creatExcel(InputStream is, boolean isExcel2003){
        List<BusinessData> businessDataList=new ArrayList<BusinessData>();
        try{
            Workbook wb =new XSSFWorkbook(is);
           businessDataList = readExcelValue(wb);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return  businessDataList;
    }
    private List<BusinessData> readExcelValue(Workbook wb) throws ParseException {
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<BusinessData> businessDataList = new ArrayList<BusinessData>();
        for (int r = 1; r <this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            BusinessData businessData = new BusinessData();
           // Map<String,Boolean> map = new HashMap<String, Boolean>();
            for ( int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    if(c==0){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String empName = cell.getStringCellValue();
                        if(getByName(empName)==null){
                            businessData.setEmpName(empName);
                    }
                        else{
                            c = this.totalCells+1;//存在则跳出大循环
                        }
                    }
                    if(c==1){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setSaleState(cell.getStringCellValue());

                    }
                    if(c==2){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setLegalRepe(cell.getStringCellValue());

                    }
                    if(c==3){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setRegCapital(cell.getStringCellValue());

                    }
                    if(c==4){
                        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                            businessData.setDate(date);
                        }
                        else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                            String value = cell.getStringCellValue();
                            if(value==""||value=="--"){
                                r = this.totalRows;
                            }
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(value);
                            businessData.setDate(date);
                        }
                        else {
                            Date date = cell.getDateCellValue();
                            businessData.setDate(date);
                        }
                    }
                    if(c==5){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setSheng(cell.getStringCellValue());

                    }
                    if(c==6){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setCity(cell.getStringCellValue());

                    }
                    if(c==7){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setPhone(cell.getStringCellValue());

                    }
                    if(c==8){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setMorePhone(cell.getStringCellValue());

                    }
                    if(c==9){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setEmail(cell.getStringCellValue());

                    }
                    if(c==10){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setSocialCode(cell.getStringCellValue());

                    }
                    if(c==11){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setTaxerId(cell.getStringCellValue());

                    }
                    if(c==12){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setRegistId(cell.getStringCellValue());

                    }
                    if(c==13){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setOssicationCode(cell.getStringCellValue());

                    }
                    if(c==14){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        businessData.setInsuranceNum((int)cell.getNumericCellValue());

                    }
                    if(c==15){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setEmpType(cell.getStringCellValue());

                    }
                    if(c==16){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setIndustory(cell.getStringCellValue());

                    }
                    if(c==17){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setUrl(cell.getStringCellValue());

                    }
                    if(c==18){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setAddress(cell.getStringCellValue());
                    }
                    if(c==19){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        businessData.setBusinessScope(cell.getStringCellValue());
                    }
                }

            }
            if(businessData!=null&&businessData.getEmpName()!=null&&!businessDataList.contains(businessData)) {
                businessDataList.add(businessData);
            }
        }
        return  businessDataList;
    }
    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+/.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath)  {


        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
//    public static boolean isExcel2016(String filePath)  {
//        return filePath.matches("^.+\\.(?i)(xlsx)$");
//    }
}


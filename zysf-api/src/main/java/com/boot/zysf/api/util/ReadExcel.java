package com.boot.zysf.api.util;

import com.boot.zysf.api.po.CompanyBasicInfo;
import jxl.CellType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadExcel {
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public ReadExcel() {
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

    public List<CompanyBasicInfo> getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<CompanyBasicInfo> companyBasicInfoList=new ArrayList<CompanyBasicInfo>();
        try{
//            if(!validateExcel(fileName)){
//                return  null;
//            }
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            System.out.println(isExcel2003);

           companyBasicInfoList = creatExcel(mFile.getInputStream(),isExcel2003);
        }catch (Exception e){
            e.printStackTrace();
        }
        return companyBasicInfoList;
    }
    public List<CompanyBasicInfo> creatExcel(InputStream is, boolean isExcel2003){
        List<CompanyBasicInfo> companyBasicInfoList=new ArrayList<CompanyBasicInfo>();
        try{
            Workbook wb =new XSSFWorkbook(is);
           companyBasicInfoList = readExcelValue(wb);
    } catch (IOException e) {
            e.printStackTrace();
        }
        return  companyBasicInfoList;
    }
    private List<CompanyBasicInfo> readExcelValue(Workbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows=sheet.getPhysicalNumberOfRows();
        if(totalRows>1&&sheet.getRow(0)!=null){
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<CompanyBasicInfo> companyBasicInfoList = new ArrayList<CompanyBasicInfo>();
//        for(int r =1;r<totalRows;r++){
//            Row row = sheet.getRow(r);
//            if(row==null){
//                continue;
//            }
//            CompanyBasicInfo companyBasicInfo = new CompanyBasicInfo();
//            for(int c =0;c<this.totalCells;c++){
//                Cell cell =row.getCell(c);
//                if(null != cell){
//                    companyBasicInfo.setId(r-1);
//                    if(c==0){
//                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                        companyBasicInfo.setName(cell.getStringCellValue());
//                    }
//                    if(c==1){
//                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                       companyBasicInfo.setSaleState(cell.getStringCellValue());
//                    }
//                    if(c==2){
//                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                        companyBasicInfo.setLegalRepe(cell.getStringCellValue());
//                    }
//                    if(c==3){
//                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                        companyBasicInfo.setRegCapital(cell.getStringCellValue());
//                    }
//                    if(c==4){
//                        /*cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                        Double value = Double.parseDouble(cell.getStringCellValue().toString());
//                        Date date =DateUtil.getJavaDate(value);
//                        companyBasicInfo.setDate(date);*/
//                       // companyBasicInfo.setDate(cell.getDateCellValue());
//                       if(DateUtil.isCellDateFormatted(cell)){
//                           Date value = DateUtil.getJavaDate(cell.getNumericCellValue(), true);
//                           companyBasicInfo.setDate(value);
//                        }
//                    }
//                    if(c==5){
//                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                        companyBasicInfo.setSheng(cell.getStringCellValue());
//                    }
//                    if(c==6){
//                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                        companyBasicInfo.setCity(cell.getStringCellValue());
//                    }
//                    if(c==7){
//                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                        companyBasicInfo.setPhone(cell.getStringCellValue());
//                    }
//                    if(c==8){
//                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                        companyBasicInfo.setMorePhone(cell.getStringCellValue());
//                    }
//                    if(c==9){
//                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                        companyBasicInfo.setEmail(cell.getStringCellValue());
//                    }
//
//                }
//            }
//            companyBasicInfoList.add(companyBasicInfo);
//        }
        return companyBasicInfoList;
    }
    public boolean validateExcel(String filePath){
        if(filePath ==null || !(isExcel2003(filePath)||!isExcel2007(filePath))){
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }
    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+/.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath)  {

        String path="C:\\Users\\dell\\Desktop\\"+filePath;

        return path.matches("^.+\\.(?i)(xlsx)$");
    }
//    public static boolean isExcel2016(String filePath)  {
//        return filePath.matches("^.+\\.(?i)(xlsx)$");
//    }
}

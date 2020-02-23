package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.CompanyDetail;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.service.CompanyDetailService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DetailRead {
    @Autowired
    CompanyDetailService companyDetailService;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public DetailRead() {
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

    public List<CompanyDetail> getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<CompanyDetail> companyDetailList=new ArrayList<CompanyDetail>();
        try{
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            //System.out.println(isExcel2003);

            companyDetailList= creatExcel(mFile.getInputStream(),isExcel2003);
        }catch (Exception e){
            e.printStackTrace();
        }
        return companyDetailList;
    }
    public CompanyDetail getByName(String name){
        CompanyDetail companyDetail = new CompanyDetail().setEmpName(name);
        QueryWrapper<CompanyDetail> query = new QueryWrapper<>(companyDetail);
        CompanyDetail one = companyDetailService.getOne(query);
        return one;
    }
    public List<CompanyDetail> creatExcel(InputStream is, boolean isExcel2003){
        List<CompanyDetail> companyDetailList=new ArrayList<CompanyDetail>();
        try{
            Workbook wb =new XSSFWorkbook(is);
            companyDetailList = readExcelValue(wb);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return  companyDetailList;
    }
    private List<CompanyDetail> readExcelValue(Workbook wb) throws ParseException {
        Sheet sheet = wb.getSheetAt(3);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<CompanyDetail> companyDetailList = new ArrayList<CompanyDetail>();
        for (int r = 1; r <this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            CompanyDetail companyDetail = new CompanyDetail();
            companyDetail.setNetProfit("");
            companyDetail.setGaoXin("");
            companyDetail.setKeJi("");
            companyDetail.setUrl("");
            companyDetail.setCode("");
            companyDetail.setDate("");
            // Map<String,Boolean> map = new HashMap<String, Boolean>();
            for ( int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);

                if (cell != null) {
                    if(c==0){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String empName = cell.getStringCellValue();
                        companyDetail.setEmpName(empName);
                    }
                    if(c==1){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setIndustory1(cell.getStringCellValue());
                    }
                    if(c==2){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setIndustory2(cell.getStringCellValue());

                    }
                    if(c==3){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setIndustory3(cell.getStringCellValue());
                        c=c+2;
                    }
//                    if(c==6){
//
                    }
                    if(c==6){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setState(cell.getStringCellValue());

                    }

                    if(c==7){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setRepresent(cell.getStringCellValue());

                    }
                    if(c==8){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setRegiMoney(cell.getStringCellValue());
                        c=c+1;
                    }
                    if(c==10){

                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setDate(cell.getStringCellValue());
                        c=c+3;
                    }
                    if(c==14){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setPhone(cell.getStringCellValue());
                        c++;
                    }
                    if(c==16){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setEmail(cell.getStringCellValue());

                    }
                    if(c==17){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setSocialCode(cell.getStringCellValue());
                        c=c+2;
                    }
                    if(c==20){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setType(cell.getStringCellValue());
                    }
                    if(c==21){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setScope(cell.getStringCellValue());
                    }
                     if(c==22){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setAddress(cell.getStringCellValue());
                    }
                    if(c==26){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setIncome(cell.getStringCellValue());
                    }
                    if(c==27){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyDetail.setNetProfit(cell.getStringCellValue());
                    }
                    if(c==29){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        companyDetail.setZhuanLi((int)cell.getNumericCellValue());
                     }
                    if(c==30){
                         cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                         companyDetail.setEmployeeNum((int)cell.getNumericCellValue());
                    }
                    if(c==31){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        Integer num = (int)cell.getNumericCellValue();
                        String gaoxin =null;
                        if(num==0) {
                            gaoxin = "否";
                        }
                        else if(num==1) {
                            gaoxin = "是";
                        }
                        companyDetail.setGaoXin(gaoxin);
                     }
                    if(c==32){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        Integer num = (int)cell.getNumericCellValue();
                        String keji =null;
                        if(num==0) {
                            keji = "否";
                        }
                        else if(num==1) {
                            keji = "是";
                     }
                         companyDetail.setKeJi(keji);
                    }
                }

            if(companyDetail!=null) {
                companyDetailList.add(companyDetail);
            }
        }
        return  companyDetailList;
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
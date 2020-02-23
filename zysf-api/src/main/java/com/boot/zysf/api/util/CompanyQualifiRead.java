package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.CompanyQualification;
import com.boot.zysf.api.service.CompanyQulifiService;
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
public class CompanyQualifiRead {

    @Autowired
    CompanyQulifiService companyQulifiService;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public CompanyQualifiRead() {
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

    public List<CompanyQualification> getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<CompanyQualification> companyQualificationList=new ArrayList<CompanyQualification>();
        try{
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            //System.out.println(isExcel2003);

            companyQualificationList= creatExcel(mFile.getInputStream(),isExcel2003);
        }catch (Exception e){
            e.printStackTrace();
        }
        return companyQualificationList;
    }
    public CompanyQualification getByName(String name){
        CompanyQualification companyQualification = new CompanyQualification().setEmpName(name);
        QueryWrapper<CompanyQualification> query = new QueryWrapper<>(companyQualification);
        CompanyQualification one = companyQulifiService.getOne(query);
        return one;
    }
    public List<CompanyQualification> creatExcel(InputStream is, boolean isExcel2003){
        List<CompanyQualification> companyQualificationList=new ArrayList<CompanyQualification>();
        try{
            Workbook wb =new XSSFWorkbook(is);
            companyQualificationList = readExcelValue(wb);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return  companyQualificationList;
    }
    private List<CompanyQualification> readExcelValue(Workbook wb) throws ParseException {
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<CompanyQualification> companyQualificationList = new ArrayList<CompanyQualification>();
        for (int r = 1; r <this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            CompanyQualification companyQualification = new CompanyQualification();
            // Map<String,Boolean> map = new HashMap<String, Boolean>();
            for ( int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    if(c==0){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String empName = cell.getStringCellValue();
                        if(getByName(empName)==null){
                            companyQualification.setEmpName(empName);
                        }
                        else{
                            c = this.totalCells+1;//存在则跳出大循环
                        }
                    }
                    if(c==1){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setSocialCode(cell.getStringCellValue());

                    }
                    if(c==2){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setAdvancedTechEmp(cell.getStringCellValue());

                    }
                    if(c==3){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setAdIdentificationBody(cell.getStringCellValue());

                    }
                    if(c==4){
                        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                            companyQualification.setAdvancedTechDate(date);
                        }
                        else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                            String value = cell.getStringCellValue();
                            if(value==""||value=="--"){
                                r = this.totalRows;
                            }
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(value);
                            companyQualification.setAdvancedTechDate(date);
                        }
                        else {
                            Date date = cell.getDateCellValue();
                            companyQualification.setAdvancedTechDate(date);
                        }

                    }
                    if(c==5){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setCertificateId(cell.getStringCellValue());

                    }
                    if(c==6){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setSmallTechEmp(cell.getStringCellValue());

                    }
                    if(c==7){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setAddress(cell.getStringCellValue());

                    }
                    if(c==8){
                        if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                            companyQualification.setSmallTechDate(date);
                        }
                        else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                            String value = cell.getStringCellValue();
                            if(value==""||value=="--"){
                                r = this.totalRows;
                            }
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(value);
                            companyQualification.setSmallTechDate(date);
                        }
                        else {
                            Date date = cell.getDateCellValue();
                            companyQualification.setSmallTechDate(date);
                        }
                    }
                    if(c==9){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setSpecializedNewGiant(cell.getStringCellValue());

                    }
                    if(c==10){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        Date value = DateUtil.getJavaDate(cell.getNumericCellValue());
                        companyQualification.setSpecNewGiaDate(value);

                    }
                    if(c==11){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setProduct(cell.getStringCellValue());

                    }
                    if(c==12){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setIndustryEducationRmp(cell.getStringCellValue());

                    }
                    if(c==13){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setIndusEduIdentificationBody(cell.getStringCellValue());

                    }
                    if(c==14){
                        if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                            companyQualification.setIndusEduIdenticicationDate(date);
                        }
                        else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                            String value = cell.getStringCellValue();
                            if(value==""||value=="--"){
                                r = this.totalRows;
                            }
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(value);
                            companyQualification.setIndusEduIdenticicationDate(date);
                        }
                        else {
                            Date date = cell.getDateCellValue();
                            companyQualification.setIndusEduIdenticicationDate(date);
                        }

                    }
                    if(c==15){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setIndustrialEnterprisesAbove(cell.getStringCellValue());

                    }
                    if(c==16){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setServiceEnterprisesAbove(cell.getStringCellValue());

                    }
                    if(c==17){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setRetaileEnterprisesAbove(cell.getStringCellValue());

                    }
                    if(c==18){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setQualificationGradeConstructionEmp(cell.getStringCellValue());

                    }
                    if(c==19){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        companyQualification.setTop500(cell.getStringCellValue());
                    }
                    if(c==20){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        companyQualification.setRankTop500((int)cell.getNumericCellValue());
                    }
                }

            }
            if(companyQualification!=null&&companyQualification.getEmpName()!=null&&!companyQualificationList.contains(companyQualification)) {
                companyQualificationList.add(companyQualification);
            }
        }
        return  companyQualificationList;
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


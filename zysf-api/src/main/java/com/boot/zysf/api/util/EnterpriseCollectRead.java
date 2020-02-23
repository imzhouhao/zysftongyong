package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.EnterpriseCollect;
import com.boot.zysf.api.po.EnterpriseFinancing;
import com.boot.zysf.api.service.EnterpriseCollectService;
import com.boot.zysf.api.service.EnterpriseFinancingService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class EnterpriseCollectRead {
    @Autowired
    EnterpriseCollectService enterpriseCollectService;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public EnterpriseCollectRead() {
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

    public List<EnterpriseCollect> getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<EnterpriseCollect> enterpriseFinancingList=new ArrayList<EnterpriseCollect>();
        try{
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            //System.out.println(isExcel2003);

            enterpriseFinancingList= creatExcel(mFile.getInputStream(),isExcel2003);
        }catch (Exception e){
            e.printStackTrace();
        }
        return enterpriseFinancingList;
    }
    public EnterpriseCollect getByName(String name){
        EnterpriseCollect enterpriseCollect = new EnterpriseCollect().setEmpName(name);
        QueryWrapper<EnterpriseCollect> query = new QueryWrapper<>(enterpriseCollect);
        EnterpriseCollect one = enterpriseCollectService.getOne(query);
        return one;
    }
    public List<EnterpriseCollect> creatExcel(InputStream is, boolean isExcel2003){
        List<EnterpriseCollect> enterpriseFinancingList=new ArrayList<EnterpriseCollect>();
        try{
            Workbook wb =new XSSFWorkbook(is);
            enterpriseFinancingList = readExcelValue(wb);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return  enterpriseFinancingList;
    }
    private List<EnterpriseCollect> readExcelValue(Workbook wb) throws ParseException {
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<EnterpriseCollect> enterpriseCollectList = new ArrayList<EnterpriseCollect>();
        for (int r = 1; r <this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            EnterpriseCollect enterpriseCollect = new EnterpriseCollect();
            // Map<String,Boolean> map = new HashMap<String, Boolean>();
            for ( int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String cellValue = cell.getStringCellValue();
                    if ( StringUtil.isNullOrBlank(cellValue) ) {
                        continue;
                    }
                    if(c==1){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String empName = cell.getStringCellValue();
                        if(getByName(empName)==null){
                            enterpriseCollect.setEmpName(empName);
                        }
                        else{
                            c = this.totalCells+1;//存在则跳出大循环
                        }
                    }
                    if(c==0){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseCollect.setYear(cell.getStringCellValue());
                    }
                    if(c==2){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseCollect.setPhone(cell.getStringCellValue());
                    }
                    if(c==3){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseCollect.setSocialCode(cell.getStringCellValue());

                    }
                    if(c==4){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseCollect.setBusinessAndProduct(cell.getStringCellValue());

                    }
                    if(c==5){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseCollect.setTotalTaxPay(cell.getStringCellValue());
                    }
                    if(c==6){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseCollect.setEmployeeFinalNum(cell.getStringCellValue());

                    }
                    if(c==7){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseCollect.setTotalFinalAsset(cell.getStringCellValue());

                    }
                    if(c==8){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setNewFixedAsset(cell.getNumericCellValue());

                    }
                    if(c==9){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setTotalYearendliabilities(cell.getNumericCellValue());

                    }
                    if(c==10){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setBankloanAmount(cell.getNumericCellValue());

                    }
                    if(c==11){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setPaidInCaptial(cell.getNumericCellValue());

                    }
                    if(c==12){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setListedFinanEquality(cell.getNumericCellValue());

                    }
                    if(c==13){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setIndustryOutput(cell.getNumericCellValue());

                    }
                    if(c==14){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setOperatingIncome(cell.getNumericCellValue());
                    }
                    if(c==15){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setBusinessCost(cell.getNumericCellValue());
                    }
                    if(c==16){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setInternalEnterpriseFunds(cell.getNumericCellValue());
                    }
                    if(c==17){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setNetProfit(cell.getNumericCellValue());
                    }
                    if(c==18){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setInventionPatentNum((int)cell.getNumericCellValue());
                    }
                    if(c==19){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setRegeistTrademarkNum((int)cell.getNumericCellValue());
                    }
                    if(c==20){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setSoftwareCopyrightNum((int)cell.getNumericCellValue());
                    }
                    if(c==21){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setElectricityCost(cell.getNumericCellValue());
                    }
                    if(c==22){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setNaturalGasCost(cell.getNumericCellValue());
                    }
                    if(c==23){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        enterpriseCollect.setWaterCost(cell.getNumericCellValue());
                    }

                }

            }
            if(enterpriseCollect!=null&&enterpriseCollect.getEmpName()!=null&&!enterpriseCollectList.contains(enterpriseCollect)) {
                enterpriseCollectList.add(enterpriseCollect);
            }
        }
        return  enterpriseCollectList;
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




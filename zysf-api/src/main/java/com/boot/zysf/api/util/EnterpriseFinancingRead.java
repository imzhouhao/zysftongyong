package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.EnterpriseFinancing;
import com.boot.zysf.api.service.EnterpriseFinancingService;
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
public class EnterpriseFinancingRead {

    @Autowired
    EnterpriseFinancingService enterpriseFinancingService;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public EnterpriseFinancingRead() {
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

    public List<EnterpriseFinancing> getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<EnterpriseFinancing> enterpriseFinancingList=new ArrayList<EnterpriseFinancing>();
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
    public EnterpriseFinancing getByName(String name){
        EnterpriseFinancing enterpriseFinancing = new EnterpriseFinancing().setEmpName(name);
        QueryWrapper<EnterpriseFinancing> query = new QueryWrapper<>(enterpriseFinancing);
        EnterpriseFinancing one = enterpriseFinancingService.getOne(query);
        return one;
    }
    public List<EnterpriseFinancing> creatExcel(InputStream is, boolean isExcel2003){
        List<EnterpriseFinancing> enterpriseFinancingList=new ArrayList<EnterpriseFinancing>();
        try{
            Workbook wb =new XSSFWorkbook(is);
            enterpriseFinancingList = readExcelValue(wb);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return  enterpriseFinancingList;
    }
    private List<EnterpriseFinancing> readExcelValue(Workbook wb) throws ParseException {
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<EnterpriseFinancing> enterpriseFinancingList = new ArrayList<EnterpriseFinancing>();
        for (int r = 1; r <this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            EnterpriseFinancing enterpriseFinancing = new EnterpriseFinancing();
            // Map<String,Boolean> map = new HashMap<String, Boolean>();
            for ( int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    if(c==0){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String empName = cell.getStringCellValue();
                        if(getByName(empName)==null){
                            enterpriseFinancing.setEmpName(empName);
                        }
                        else{
                            c = this.totalCells+1;//存在则跳出大循环
                        }
                    }
                    if(c==1){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setSocialCode(cell.getStringCellValue());

                    }
                    if(c==2){
                        if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                            enterpriseFinancing.setMarketDate(date);
                        }
                        else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                            String value = cell.getStringCellValue();
                            if(value==""||value=="--"){
                                r = this.totalRows;
                            }
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(value);
                            enterpriseFinancing.setMarketDate(date);
                        }
                        else {
                            Date date = cell.getDateCellValue();
                            enterpriseFinancing.setMarketDate(date);
                        }
                    }
                    if(c==3){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setMarketModel(cell.getStringCellValue());

                    }
                    if(c==4){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setStockCode(cell.getStringCellValue());

                    }
                    if(c==5){
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        Date value = DateUtil.getJavaDate(cell.getNumericCellValue());
                        enterpriseFinancing.setFinancDate(value);
                    }
                    if(c==6){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setFinancTurn(cell.getStringCellValue());

                    }
                    if(c==7){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setFinancMoney(cell.getStringCellValue());

                    }
                    if(c==8){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setInvestor(cell.getStringCellValue());

                    }
                    if(c==9){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setCompanyValue(cell.getStringCellValue());

                    }
                    if(c==10){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setIsUnicorn(cell.getStringCellValue());

                    }
                    if(c==11){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setInvestTo(cell.getStringCellValue());

                    }
                    if(c==12){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setInvestRatio(cell.getStringCellValue());

                    }
                    if(c==13){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setInvestMoney(cell.getStringCellValue());

                    }
                    if(c==14){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseFinancing.setInvestMethod(cell.getStringCellValue());

                    }
                }

            }
            if(enterpriseFinancing!=null&&enterpriseFinancing.getEmpName()!=null&&!enterpriseFinancingList.contains(enterpriseFinancing)) {
                enterpriseFinancingList.add(enterpriseFinancing);
            }
        }
        return  enterpriseFinancingList;
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



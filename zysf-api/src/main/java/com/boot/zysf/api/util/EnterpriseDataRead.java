package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.EnterpriseData;
import com.boot.zysf.api.po.GonvernmentData;
import com.boot.zysf.api.service.EnterpriseDataService;
import com.boot.zysf.api.service.GonvernmentDataService;
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
public class EnterpriseDataRead {
    @Autowired
    EnterpriseDataService enterpriseDataService;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public EnterpriseDataRead() {
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

    public List<EnterpriseData> getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<EnterpriseData> enterpriseDataList =new ArrayList<EnterpriseData>();
        try{
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            //System.out.println(isExcel2003);

            enterpriseDataList = creatExcel(mFile.getInputStream(),isExcel2003);
        }catch (Exception e){
            e.printStackTrace();
        }
        return enterpriseDataList;
    }
    public EnterpriseData getByName(String name){
        EnterpriseData enterpriseData = new EnterpriseData().setEmpName(name);
        QueryWrapper<EnterpriseData> query = new QueryWrapper<>(enterpriseData);
        EnterpriseData one = enterpriseDataService.getOne(query);
        return one;
    }
    public List<EnterpriseData> creatExcel(InputStream is, boolean isExcel2003){
        List<EnterpriseData> enterpriseDataList =new ArrayList<EnterpriseData>();
        try{
            Workbook wb =new XSSFWorkbook(is);
            enterpriseDataList = readExcelValue(wb);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return enterpriseDataList;
    }
    private List<EnterpriseData> readExcelValue(Workbook wb) throws ParseException {
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<EnterpriseData> enterpriseDataList = new ArrayList<EnterpriseData>();
        for (int r = 1; r <this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            EnterpriseData enterpriseData = new EnterpriseData();
            // Map<String,Boolean> map = new HashMap<String, Boolean>();
            for ( int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);

            if (cell != null ) {

                    if(c==3){//公司名称
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String cellValue = cell.getStringCellValue();
                        if ( StringUtil.isNullOrBlank(cellValue) ) {
                            continue;
                        }
                        String empName = cell.getStringCellValue();
                        if(getByName(empName)==null&empName!="--"){
                            enterpriseData.setEmpName(empName);
                        }
                        else{
                            c = this.totalCells;//存在则跳出大循环
                        }
                    }
                    if(c==0){// 上市板块
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setMarketModel(cell.getStringCellValue());
                    }
                    if(c==1){//股票代码
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setStockCode(cell.getStringCellValue());
                    }
                    if(c==2){//股票简称
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setStockAbbreviation(cell.getStringCellValue());

                    }
                    if(c==4){//城市
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setCity(cell.getStringCellValue());

                    }
                    if(c==5){  //行政区
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setRegion(cell.getStringCellValue());
                    }
                    if(c==6){ //主营业务收入
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setMainOperatingIncome(cell.getStringCellValue());

                    }
                    if(c==7){//主营业务收入qianyuan
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setMainOperatingIncomeThounsand(cell.getStringCellValue());

                    }
                    if(c==8){//净利润
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setNetProfit(cell.getStringCellValue());

                    }
                    if(c==9){//净利润（千元）
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setNetProfitThousand(cell.getStringCellValue());

                    }
                    if(c==10){ //员工人数
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setEmployeeNum(cell.getStringCellValue());
                    }
                    if(c==11){//上市日期
                        if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                            enterpriseData.setMarketDate(date);
                        }
                        else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                            String value = cell.getStringCellValue();
                            if(value==""||value=="--"){
                                r = this.totalRows;
                            }
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(value);
                            enterpriseData.setMarketDate(date);
                        }
                        else {
                            Date date = cell.getDateCellValue();
                            enterpriseData.setMarketDate(date);
                        }                    }
                    if(c==12){//成立日期
                        if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
                            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
                            enterpriseData.setEstablishDate(date);
                        }
                        else if(cell.getCellType()==Cell.CELL_TYPE_STRING){
                            String value = cell.getStringCellValue();
                            if(value==""||value=="--"){
                                r = this.totalRows;
                            }
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(value);
                            enterpriseData.setEstablishDate(date);
                        }
                        else {
                            Date date = cell.getDateCellValue();
                            enterpriseData.setEstablishDate(date);
                        }
                    }
                    if(c==13){//招股书
                        enterpriseData.setProspectus(cell.getStringCellValue());

                    }
                    if(c==14){//行业分类
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setIndustory(cell.getStringCellValue());

                    }
                    if(c==15){//产品类型
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setPruductType(cell.getStringCellValue());
                    }
                    if(c==16){//主营业务
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        enterpriseData.setMainBusiness(cell.getStringCellValue());
                    }
                }

            }
            if(enterpriseDataList !=null&& enterpriseData.getEmpName()!=null&&!enterpriseDataList.contains(enterpriseData)) {
                enterpriseDataList.add(enterpriseData);
            }
        }
        return enterpriseDataList;
    }
    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+/.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath)  {



        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}

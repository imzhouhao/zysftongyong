package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.GonvernmentData;
import com.boot.zysf.api.service.GonvernmentDataService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@Component
public class GovernmentDataRead {

    @Autowired
    GonvernmentDataService gonvernmentDataService;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public GovernmentDataRead() {
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

    public List<GonvernmentData> getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<GonvernmentData> gonvernmentDataList =new ArrayList<GonvernmentData>();
        try{
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            //System.out.println(isExcel2003);

            gonvernmentDataList = creatExcel(mFile.getInputStream(),isExcel2003);
        }catch (Exception e){
            e.printStackTrace();
        }
        return gonvernmentDataList;
    }
    public GonvernmentData getByName(String name){
        GonvernmentData gonvernmentData = new GonvernmentData().setEmpName(name);
        QueryWrapper<GonvernmentData> query = new QueryWrapper<>(gonvernmentData);
        GonvernmentData one = gonvernmentDataService.getOne(query);
        return one;
    }
    public List<GonvernmentData> creatExcel(InputStream is, boolean isExcel2003){
        List<GonvernmentData> gonvernmentDataList =new ArrayList<GonvernmentData>();
        try{
            Workbook wb =new XSSFWorkbook(is);
            gonvernmentDataList = readExcelValue(wb);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return gonvernmentDataList;
    }
    private List<GonvernmentData> readExcelValue(Workbook wb) throws ParseException {
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<GonvernmentData> gonvernmentDataList = new ArrayList<GonvernmentData>();
        for (int r = 1; r <this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            GonvernmentData gonvernmentData = new GonvernmentData();
            // Map<String,Boolean> map = new HashMap<String, Boolean>();
            for ( int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (cell != null ) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String cellValue = cell.getStringCellValue();
                    if ( StringUtil.isNullOrBlank(cellValue) ) {
                        continue;
                    }
                    if(c==2){
                        String empName = cell.getStringCellValue();
                        if(getByName(empName)==null){
                            gonvernmentData.setEmpName(empName);
                        }
                        else{
                            c = this.totalCells;//存在则跳出大循环
                        }
                    }
                    if(c==0){
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setYear(cell.getStringCellValue());
                    }
                    if(c==1){//组织机构代码
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setOrganizationalCode(cell.getStringCellValue());
                    }
                    if(c==3){//统一社会信用代码
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setSocialCode(cell.getStringCellValue());

                    }
                    if(c==4){//组织机构代码
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setOrganizationalCode(cell.getStringCellValue());

                    }
                    if(c==5){ //从业人员期末人数
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setEmployeeFinalNum(cell.getStringCellValue());
                    }
                    if(c==6){//年末资产总计
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setTotalFinalAsset(cell.getStringCellValue());

                    }
                    if(c==7){//其中：流动资产合计
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setLiquidAsset(cell.getStringCellValue());

                    }
                    if(c==8){//其中：固定资产合计
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setFixedAsset(cell.getStringCellValue());

                    }
                    if(c==9){//其中：累计折旧
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setAccumulatedDepreciation(cell.getStringCellValue());

                    }
                    if(c==10){//本年完成固定资产投资额
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setFixedAssetInvest(cell.getStringCellValue());

                    }
                    if(c==11){//无形资产
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setIntangibleAsset(cell.getStringCellValue());

                    }
                    if(c==12){//非流动资产合计
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setUnLiquidAsset(cell.getStringCellValue());

                    }
                    if(c==13){//年末负债合计
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setTotalYearendliabilities(cell.getStringCellValue());

                    }
                    if(c==14){//银行贷款额
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setBankloanAmount(cell.getStringCellValue());
                    }
                    if(c==15){//本年应付职工薪酬
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setEmployeeSalary(cell.getStringCellValue());
                    }
                    if(c==16){ //年末所有者权益（股东权益）
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setShareholderSalary(cell.getStringCellValue());
                    }
                    if(c==17){  //实收资本（股本
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setStockValue(cell.getStringCellValue());
                    }
                    if(c==18){//企业上市融资股本
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setListedFinanEquality(cell.getStringCellValue());
                    }
                    if(c==19){//工业总产值
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setIndustryOutput(cell.getStringCellValue());
                    }
                    if(c==20){ //营业收入
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setOperatingIncome(cell.getStringCellValue());
                    }
                    if(c==21){//主营业务收入
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setMainOperatingIncome(cell.getStringCellValue());
                    }
                    if(c==22){//产品销售收入
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setProductSaleIncome(cell.getStringCellValue());
                    }
                    if(c==23){//商品销售收入
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setCommoditySaleIncome(cell.getStringCellValue());
                    }
                    if(c==24){//营业成本
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setBusinessCost(cell.getStringCellValue());
                    }
                    if(c==25){//销售费用
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setSellingExpense(cell.getStringCellValue());
                    }
                    if(c==26){//管理费用
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setManageExpense(cell.getStringCellValue());
                    }
                    if(c==27){//财务费用
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setFinanceExpense(cell.getStringCellValue());
                    }
                    if(c==28){ //研发、试验检验费  采集表 企业 R&D经费内部支出(千元)
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setInternalEnterpriseFunds(cell.getStringCellValue());
                    }if(c==29){//营业利润
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setSaleProfit(cell.getStringCellValue());
                    }
                    if(c==30){//营业外收入
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setOutBusinessIncome(cell.getStringCellValue());
                    }
                    if(c==31){//营业外支出
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setOutBusinessCost(cell.getStringCellValue());
                    }
                    if(c==32){//利润总额
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setTotalProfit(cell.getStringCellValue());
                    }
                    if(c==33){ //净利润
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setNetProfit(cell.getStringCellValue());
                    }
                    if(c==34){//所得税
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        gonvernmentData.setTax(cell.getStringCellValue());
                    }

                }

            }
            if(gonvernmentDataList !=null&& gonvernmentData.getEmpName()!=null&&!gonvernmentDataList.contains(gonvernmentData)&&gonvernmentData.getEmpName()!="") {
                gonvernmentDataList.add(gonvernmentData);
            }
        }
        return gonvernmentDataList;
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




package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.Company;
import com.boot.zysf.api.po.CompanyQualification;
import com.boot.zysf.api.service.CompanyQulifiService;
import com.boot.zysf.api.service.ICompanyService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class CompanyRead {

    @Autowired
    ICompanyService iCompanyService;
    private int totalRows = 0;
    private int totalCells = 0;
    private String errorMsg;

    public CompanyRead() {
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

    public void getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();
        List<CompanyQualification> companyQualificationList = new ArrayList<CompanyQualification>();
        try {
            boolean isExcel2003 = true;
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            //System.out.println(isExcel2003);
            creatExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Company getByName(String name) {
        Company company = new Company().setName(name);
        QueryWrapper<Company> query = new QueryWrapper<>(company);
        Company one = iCompanyService.getOne(query);
        return one;
    }

    public void creatExcel(InputStream is, boolean isExcel2003) {
        List<Company> companyQualificationList = new ArrayList<Company>();
        try {
            Workbook wb = new XSSFWorkbook(is);
            readExcelValue(wb);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void readExcelValue(Workbook wb) throws ParseException {
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        for (int r = 7000; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            Company company = new Company();
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String cellValue = cell.getStringCellValue();
                    if (StringUtil.isNullOrBlank(cellValue)) {
                        continue;
                    }
                    if (c == 0) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String name = cell.getStringCellValue();
                        company.setName(name);
                    }
                    if (c == 1) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String code = cell.getStringCellValue();
                        company.setCode(code);
                    }
                    if (c == 2) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String add = cell.getStringCellValue();
                        company.setAddress(add);
                    }
                    if (c == 3) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        double lng = Double.parseDouble(cell.getStringCellValue());
                        company.setLng(lng);
                    }
                    if (c == 4) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        double lat = Double.parseDouble(cell.getStringCellValue());
                        company.setLat(lat);
                    }
                }
            }
            company.setUtime(LocalDateTime.now());
            company.setCtime(LocalDateTime.now());
            iCompanyService.save(company);
        }
    }

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+/.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {

        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
//    public static boolean isExcel2016(String filePath)  {
//        return filePath.matches("^.+\\.(?i)(xlsx)$");
//    }
}
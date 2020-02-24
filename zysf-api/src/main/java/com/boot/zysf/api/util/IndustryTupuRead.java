package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.IndustrialCategory;
import com.boot.zysf.api.po.IndustroyTupu;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import com.boot.zysf.api.service.IndustroyTupuService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class IndustryTupuRead {
    @Autowired
    IndustroyTupuService industroyTupuService;

    private  CharacterUtil characterUtil;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public IndustryTupuRead() {
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

    public Integer getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<IndustroyTupu> industryTupuList=new ArrayList<IndustroyTupu>();
        try{
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            //System.out.println(isExcel2003);

            Integer integer = creatExcel(mFile.getInputStream(), isExcel2003);
            return integer;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }
    public IndustroyTupu getByName(String name,Integer parentId){
        IndustroyTupu industroyTupu = new IndustroyTupu().setName(name).setParentId(parentId);
        QueryWrapper<IndustroyTupu> query = new QueryWrapper<>(industroyTupu);
        IndustroyTupu one = industroyTupuService.getOne(query);
        return one;
    }
    public Integer creatExcel(InputStream is, boolean isExcel2003){
        List<IndustroyTupu> industroyTupuList=new ArrayList<IndustroyTupu>();
        try{
            Workbook wb =new XSSFWorkbook(is);
            Integer integer = readExcelValue(wb);
            return integer;
        } catch (IOException e) {
            e.printStackTrace();
            return  -1;
        }
    }
    private Integer readExcelValue(Workbook wb) {
        Integer count = 0;
        Sheet sheet = null;
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            sheet = wb.getSheetAt(i);
            this.totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows > 1 && sheet.getRow(2) != null) {
                this.totalCells = sheet.getRow(2).getPhysicalNumberOfCells();
            }
            List<IndustroyTupu> industroyTupuList = new ArrayList<IndustroyTupu>();
            //!!test
            Integer id = 0;
            for (int r = 1; r < this.totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                IndustroyTupu industroyTupu = new IndustroyTupu();
                String relation;
                Integer parentId = -1;
                for (int c = 0; c < this.totalCells; c++) {
                    Cell cell = row.getCell(c);
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String cellValue = cell.getStringCellValue();
                        if (StringUtil.isNullOrBlank(cellValue)) {
                            continue;
                        }
                        if (c == 0) {//一级行业
                            String value = cell.getStringCellValue();
                            IndustroyTupu category = getByName(value,parentId);
                            if (category == null) {
                                industroyTupu.setName(value);
                                industroyTupu.setParentId(-1);
                                industroyTupuService.save(industroyTupu);
                                parentId = industroyTupu.getId();
                                count++;
                            } else {
                                parentId = category.getId();
                            }
                        }
                        if (c == 1) {//二级行业
                            Cell cell2 = row.getCell(c);
                            cell2.setCellType(Cell.CELL_TYPE_STRING);
                            relation = cell2.getStringCellValue();
                            if (StringUtil.isNullOrBlank(relation)) {
                                continue;
                            }
                            Cell cell1 = row.getCell(c + 1);
                            String value = cell1.getStringCellValue();
                            IndustroyTupu category = getByName(value,parentId);
                            if (category == null) {
                                industroyTupu.setName(value);
                                industroyTupu.setParentId(parentId);
                                industroyTupu.setRelation(relation);
                                industroyTupuService.save(industroyTupu);
                                parentId = industroyTupu.getId();
                                count++;
                            } else {
                                parentId = category.getId();
                            }
                            c = c + 2;
                        }
                        if (c == 3) {
                            //三级行业
                            Cell cell2 = row.getCell(c);
                            if (cell2 == null) {
                                continue;
                            }
                            cell2.setCellType(Cell.CELL_TYPE_STRING);
                            relation = cell2.getStringCellValue();
                            if (StringUtil.isNullOrBlank(relation)) {
                                continue;
                            }
                            Cell cell1 = row.getCell(c + 1);
                            String value = cell1.getStringCellValue();
                            IndustroyTupu category = getByName(value,parentId);
                            if (category == null) {

                                industroyTupu.setName(value);
                                industroyTupu.setParentId(parentId);
                                industroyTupu.setRelation(relation);
                                industroyTupuService.save(industroyTupu);
                                parentId = industroyTupu.getId();
                                count++;
                            } else {
                                parentId = category.getId();
                            }
                            c = c + 2;
                        }
                        if (c == 5) {
                            //三级行业
                            Cell cell2 = row.getCell(c);
                            if (cell2 == null) {
                                continue;
                            }
                            cell2.setCellType(Cell.CELL_TYPE_STRING);
                            relation = cell2.getStringCellValue();
                            if (StringUtil.isNullOrBlank(relation)) {
                                continue;
                            }
                            Cell cell1 = row.getCell(c + 1);
                            String value = cell1.getStringCellValue();
                            IndustroyTupu category = getByName(value,parentId);
                            if (category== null) {

                                industroyTupu.setName(value);
                                industroyTupu.setParentId(parentId);
                                industroyTupu.setRelation(relation);
                                industroyTupuService.save(industroyTupu);
                                parentId = industroyTupu.getId();
                                count++;
                            } else {
                                parentId = category.getId();
                            }
                            c = c + 2;
                        }

                        if (c == 7) {
                            //三级行业
                            Cell cell2 = row.getCell(c);
                            if (cell2 == null) {
                                continue;
                            }
                            cell2.setCellType(Cell.CELL_TYPE_STRING);
                            relation = cell2.getStringCellValue();
                            if (StringUtil.isNullOrBlank(relation)) {
                                continue;
                            }
                            Cell cell1 = row.getCell(c + 1);
                            String value = cell1.getStringCellValue();
                            IndustroyTupu category = getByName(value,parentId);
                            if (category== null) {
                                industroyTupu.setName(value);
                                industroyTupu.setParentId(parentId);
                                industroyTupu.setRelation(relation);
                                industroyTupuService.save(industroyTupu);
                                parentId = industroyTupu.getId();
                                count++;
                            } else {
                                parentId =category.getId();
                            }
                            c = c + 2;
                        }

                  /*  if(c==9) {
                        //5级行业
                        Cell cell2 = row.getCell(c);
                        cell2.setCellType(Cell.CELL_TYPE_STRING);
                        relation = cell.getStringCellValue();
                        Cell cell1 = row.getCell(c+1);
                        String value = cell1.getStringCellValue();
                        if(getByName(value)==null){
                            industroyTupu.setName(value);
                            industroyTupu.setParentId(parentId);
                            industroyTupu.setRelation(relation);
                            industroyTupuService.save(industroyTupu);
                            parentId = industroyTupu.getId();
                        }
                        else {
                            parentId = getByName(value).getId();
                        }
                        c=c+2;
                    }*/

                    }
                }

            }
        }
        return count;
    }
    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+/.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath)  {

        // String path="C:\\Users\\dell\\Desktop\\"+filePath;

        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
//    public static boolean isExcel2016(String filePath)  {
//        return filePath.matches("^.+\\.(?i)(xlsx)$");
//    }

}

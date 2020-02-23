package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.mapper.IndustrialCategoryMapper;
import com.boot.zysf.api.po.CompanyBasicInfo;
import com.boot.zysf.api.po.IndustrialCategory;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import io.swagger.models.auth.In;
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
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Component
public class IndustryRead {
    @Autowired
    IIndustrialCategoryService iIndustrialCategoryService;


    private  CharacterUtil characterUtil;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public IndustryRead() {
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

    public void getExcelInfo(MultipartFile mFile){
        String fileName = mFile.getOriginalFilename();
        List<IndustrialCategory> industrialCategoryList=new ArrayList<IndustrialCategory>();
        try{
            boolean isExcel2003 = true;
            if(isExcel2007(fileName)){
                isExcel2003=false;
            }
            //System.out.println(isExcel2003);

            creatExcel(mFile.getInputStream(),isExcel2003);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public IndustrialCategory getByName(String name){
        IndustrialCategory industrialCategory = new IndustrialCategory().setName(name);
        QueryWrapper<IndustrialCategory> query = new QueryWrapper<>(industrialCategory);
        IndustrialCategory one = iIndustrialCategoryService.getOne(query);
        return one;
    }
    public void creatExcel(InputStream is, boolean isExcel2003){
        List<IndustrialCategory> industrialCategoryList=new ArrayList<IndustrialCategory>();
        try{
            Workbook wb =new XSSFWorkbook(is);
            readExcelValue(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readExcelValue(Workbook wb) {
        Sheet sheet = null;
        for (int i = 0; i < 2; i++) {
            sheet = wb.getSheetAt(i);
            this.totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows > 1 && sheet.getRow(2) != null) {
                this.totalCells = sheet.getRow(2).getPhysicalNumberOfCells();
            }
            String code = null;
            Integer yijihangyeid = -1;
            IndustrialCategory industrialCategory = new IndustrialCategory();
            Cell cellone = sheet.getRow(0).getCell(1);
            if(cellone!=null){
                String yijihangye = cellone.getStringCellValue();
                if(getByName(yijihangye)==null) {
                    industrialCategory.setCode(code);
                    industrialCategory.setParentId(-1);
                    industrialCategory.setName(yijihangye);
                    iIndustrialCategoryService.save(industrialCategory);
                    yijihangyeid = industrialCategory.getId();
                }
            }
            for (int r = 2; r < totalRows; r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                for (int c = 0; c < this.totalCells; c++) {
                    Integer parentId=0;
                    Cell cell = row.getCell(c);
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String cellValue = cell.getStringCellValue();
                        if (StringUtil.isNullOrBlank(cellValue)) {
                            continue;
                        }
                        if (c == 0) {//二级行业
                            code = cell.getStringCellValue();
                            Cell cell1 = row.getCell(c + 1);
                            cell1.setCellType(Cell.CELL_TYPE_STRING);
                            String value = cell1.getStringCellValue();
                            if (getByName(value)==null) {
                                industrialCategory.setName(value);
                                industrialCategory.setParentId(yijihangyeid);
                                industrialCategory.setCtime(LocalDateTime.now());
                                industrialCategory.setUtime(LocalDateTime.now());
                                industrialCategory.setCode(code);
                                iIndustrialCategoryService.save(industrialCategory);
                                parentId = industrialCategory.getId();
                            } else {
                                parentId = getByName(value).getId();
                            }
                            c = c + 2;
                        }
                        if (c == 2) {//三级行业
                            Cell cell2 = row.getCell(c);
                            cell2.setCellType(Cell.CELL_TYPE_STRING);
                            code = cell2.getStringCellValue();
                            Cell cell1 = row.getCell(c + 1);
                            cell1.setCellType(Cell.CELL_TYPE_STRING);
                            String value = cell1.getStringCellValue();
                            if (getByName(value)==null) {
                                industrialCategory.setName(value);
                                industrialCategory.setParentId(parentId);
                                industrialCategory.setCtime(LocalDateTime.now());
                                industrialCategory.setUtime(LocalDateTime.now());
                                industrialCategory.setCode(code);
                                iIndustrialCategoryService.save(industrialCategory);
                                parentId = industrialCategory.getId();
                            } else {
                                parentId = getByName(value).getId();
                            }
                            c = c + 2;
                        }
                        if (c == 4) {
                            //四级行业
                            Cell cell2 = row.getCell(c);
                            if(cell2==null){
                                continue;
                            }
                            cell2.setCellType(Cell.CELL_TYPE_STRING);
                            code = cell2.getStringCellValue();
                            if("".equals(code)){
                                continue;
                            }
                            Cell cell1 = row.getCell(c + 1);
                            cell1.setCellType(Cell.CELL_TYPE_STRING);
                            String value = cell1.getStringCellValue();
                            if (getByName(value)==null) {
                                industrialCategory.setName(value);
                                industrialCategory.setParentId(parentId);
                                industrialCategory.setCtime(LocalDateTime.now());
                                industrialCategory.setUtime(LocalDateTime.now());
                                industrialCategory.setCode(code);
                                iIndustrialCategoryService.save(industrialCategory);
                                parentId = industrialCategory.getId();
                            } else {
                                parentId = getByName(value).getId();
                            }
                            c = c + 2;
                        }

                        if (c == 6) {//五级行业
                            Cell cell2 = row.getCell(c);
                            if(cell2==null){
                                continue;
                            }
                            cell2.setCellType(Cell.CELL_TYPE_STRING);
                            String value = cell2.getStringCellValue();
                            if("".equals(value)){
                                continue;
                            }
                            Cell o =row.getCell(c+1);
                            if((o==null||"".equals(o.getStringCellValue()))&&getByName(value)==null){//没有六级行业，五级行业作为关键词分词
                                String name =CharacterUtil.deleteChareacter(value);
                                industrialCategory.setName(value);
                                industrialCategory.setParentId(parentId);
                                industrialCategory.setCtime(LocalDateTime.now());
                                industrialCategory.setUtime(LocalDateTime.now());
                                industrialCategory.setCode("last");
                                iIndustrialCategoryService.save(industrialCategory);
                                c=c+this.totalCells;
                            }
                            else {//存在六级行业，
                                Cell cell1 = row.getCell(c+1);
                                if(cell1==null){
                                    continue;
                                }
                                cell1.setCellType(Cell.CELL_TYPE_STRING);
                                String name = cell1.getStringCellValue();
                                if(getByName(name)==null) {
                                    industrialCategory.setName(name);
                                    industrialCategory.setParentId(parentId);
                                    industrialCategory.setCtime(LocalDateTime.now());
                                    industrialCategory.setUtime(LocalDateTime.now());
                                    industrialCategory.setCode(value);
                                    iIndustrialCategoryService.save(industrialCategory);
                                    parentId = industrialCategory.getId();
                                }
                                Cell cell3 = row.getCell(c+2);
                                if(cell3!=null){
                                    cell3.setCellType(Cell.CELL_TYPE_STRING);
                                    name = cell3.getStringCellValue();
                                    if(getByName(name)==null) {
                                        industrialCategory.setName(name);
                                        industrialCategory.setParentId(parentId);
                                        industrialCategory.setCtime(LocalDateTime.now());
                                        industrialCategory.setUtime(LocalDateTime.now());
                                        industrialCategory.setCode("last");
                                        iIndustrialCategoryService.save(industrialCategory);
                                    }
                                    c = this.totalCells;
                                }
                            }
                        }
                    }
                }

            }
        }
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

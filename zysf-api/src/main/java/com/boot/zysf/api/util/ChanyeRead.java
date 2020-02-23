
package com.boot.zysf.api.util;

        import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
        import com.boot.zysf.api.dto.Location;
        import com.boot.zysf.api.mapper.InduMapper;
        import com.boot.zysf.api.po.Indu;
        import com.boot.zysf.api.po.IndustrialCategory;
        import com.boot.zysf.api.service.IIndustrialCategoryService;
        import com.boot.zysf.api.service.InduService;
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
public class ChanyeRead {

    @Autowired
    InduService induService;
    // @Autowired
    //InduMapper induMapper;
    private  CharacterUtil characterUtil;
    private int totalRows = 0;
    private int totalCells =0;
    private String errorMsg;

    public ChanyeRead() {
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
        List<Indu> industrialCategoryList=new ArrayList<Indu>();
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
    public Indu getByName(String label){
        Indu indu = new Indu().setLabel(label);
        QueryWrapper<Indu> query = new QueryWrapper<>(indu);
        Indu one = induService.getOne(query);
        return one;
    }
    public void creatExcel(InputStream is, boolean isExcel2003){
        List<Indu> induList=new ArrayList<>();
        try{
            Workbook wb =new XSSFWorkbook(is);
            readExcelValue(wb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readExcelValue(Workbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows > 1 && sheet.getRow(2) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Indu> induList = new ArrayList<Indu>();
        //!!test
        for (int r = 1; r <this.totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            Indu indu = new Indu();
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if(cell!=null){
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String cellValue = cell.getStringCellValue();
                    if ( StringUtil.isNullOrBlank(cellValue) ) {
                        continue;
                    }
                    if(c==0){//一级行业
                       String label = cell.getStringCellValue();
                       indu.setNumbers(0);
                       indu.setLabel(label);
                       indu.setCategory(c);
                       indu.setSymbolSize(20);
                       indu.setIgnores(true);
                       indu.setFlag(false);
                       indu.setMoney(0);
                       indu.setLat(0);
                       indu.setLng(0);
                       induService.save(indu);
                       indu.setName(indu.getId().toString());
                       induService.saveOrUpdate(indu);
                    }
                    if(c==1){//2级行业
                        String label = cell.getStringCellValue();
                        indu.setNumbers(0);
                        indu.setLabel(label);
                        indu.setCategory(c);
                        indu.setSymbolSize(20);
                        indu.setIgnores(true);
                        indu.setFlag(true);
                        indu.setMoney(0);
                        indu.setLat(0);
                        indu.setLng(0);
                        induService.save(indu);
                        indu.setName(indu.getId().toString());
                        induService.saveOrUpdate(indu);
                    }
                    if(c==2){//3级行业
                        String label = cell.getStringCellValue();
                        indu.setNumbers(0);
                        indu.setLabel(label);
                        indu.setCategory(c);
                        indu.setSymbolSize(20);
                        indu.setIgnores(false);
                        indu.setFlag(true);
                        indu.setMoney(0);
                        indu.setLat(0);
                        indu.setLng(0);
                        induService.save(indu);
                        indu.setName(indu.getId().toString());
                        induService.saveOrUpdate(indu);
                    }
                    if(c==3){//4级行业
                        String label = cell.getStringCellValue();
                        indu.setNumbers(0);
                        indu.setLabel(label);
                        indu.setCategory(c);
                        indu.setSymbolSize(20);
                        indu.setIgnores(false);
                        indu.setFlag(true);
                        indu.setMoney(0);
                        indu.setLat(0);
                        indu.setLng(0);
                        induService.save(indu);
                        indu.setName(indu.getId().toString());
                        induService.saveOrUpdate(indu);
                    }
                    if(c==4){//5级行业
                        String label = cell.getStringCellValue();
                        indu.setNumbers(0);
                        indu.setLabel(label);
                        indu.setCategory(c);
                        indu.setSymbolSize(20);
                        indu.setIgnores(false);
                        indu.setFlag(true);
                        indu.setMoney(0);
                        indu.setLat(0);
                        indu.setLng(0);
                        induService.save(indu);
                        indu.setName(indu.getId().toString());
                        induService.saveOrUpdate(indu);
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

package com.zcc.utils.excelExport;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelExport {

    public static void main(String[] args) {
        ExcelExport ee = new ExcelExport();
        ee.writeExcel2();
    }

    public void writeExcel2() {
        // 声明excel对象
        HSSFWorkbook wb = null;
        // 声明poi流
        POIFSFileSystem fs = null;
        // 写读取的文件路径
        String path = "C:\\Users\\86151\\Desktop\\company\\楼宇租赁\\excel模板\\租客导入模板.xls";
        try {

            // 设置要读取的文件路径
            // 创建文件流
            fs = new POIFSFileSystem(new FileInputStream(path));

            // HSSFWorkBook相当于一个excel文件，HSSFWorkBook是解析excel2007以前的版本（xls）
            // 之后的版本使用XSSFWrokBook（xlsx）
            // 创建excell对象
            wb = new HSSFWorkbook(fs);

            // 获得工作薄
            // 得到工作表
            HSSFSheet sheet = wb.getSheetAt(0);

//            boolean isOne = true;
            //区域选择
            CellRangeAddressList cellRangeHireStatusList = new CellRangeAddressList(3, 65535, 4, 4);
            DataValidationHelper dataValidationHelper = sheet.getDataValidationHelper();
            // 构造下拉框和数据
            DataValidationConstraint constraintHireStatus = dataValidationHelper.createExplicitListConstraint(new String[]{"ds", "ds", "ds", "ds", "ds"});
            // 绑定下拉框和区域
            DataValidation validation1 = dataValidationHelper.createValidation(constraintHireStatus, cellRangeHireStatusList);
            // 为sheet添加验证
            sheet.addValidationData(validation1);

//            for (Iterator<Row> iter = sheet.rowIterator(); iter.hasNext();) {
//                // 得到行
//                Row row = iter.next();
//                // 迭代列
//                // 循环每一行的所有列
//                int i = 0;
//                for (Iterator<Cell> cellIter = row.cellIterator(); cellIter.hasNext();) {
//                    // 得到列对象
//                    Cell cell = cellIter.next();
//                    if (i == 1) {
////                        String content = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? cell.getNumericCellValue() + "": cell.getStringCellValue();
////                        if(!content.equals("项目名称")){
////                            cell.setCellValue("****项目");
////                        }
////                        cell.
//                    }
//
//                    i++;
//                }
//                if (isOne) {
//
//                    isOne = false;
//                }
//            }

            String path2 = "C:\\Users\\86151\\Desktop\\company\\楼宇租赁\\excel模板\\2.xls";
            // 创建输出流
            OutputStream out = new FileOutputStream(path2);
            // 将数据写入文件
            wb.write(out);
            // 关闭文件
            out.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
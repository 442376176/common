package com.zcc.utils.excelExport;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.File;



/**
 * @ Author Zcc
 * @ Date 2022/8/13 10:45
 * @ Describe : ExportTemplate
 */
public class ExportTemplate {
    private static final int XLS_MAX_ROW = 65535; //0开始

    public static void main(String[] args) throws Exception {
        create();
    }

    public static void create() throws Exception {
        //首行中文标题
        String[] titleCNName = {"租客名称（必填）", "证件号码（必填）", "租客联系电话（选填）",
                "邮箱（选填）", "所属行业（选填）"};

        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个工作表sheet
        HSSFSheet sheet = workbook.createSheet("工作表1");
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("卓瓴租客导入模板");
        HSSFCellStyle style = workbook.createCellStyle();
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);//设置自动换行
        HSSFFont font = workbook.createFont();
        font.setBold(true);//粗体
        font.setFontName("宋体");//设置字体名称
        font.setFontHeightInPoints((short) 16);//设置字号
        style.setFont(font);
        cell.setCellStyle(style);
        row.setHeightInPoints(25);//设置行高
        //合并
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                titleCNName.length - 1 // last column
        );
        sheet.addMergedRegion(region);
        //第二行
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("填写注意事项：（未按照如下要求填写，会导致数据不能正常导入）\n" +
                " 1、请不要修改此表格的格式，包括插入删除行和列、合并拆分单元格等。需要填写的单元格有字段规则校验，请按照要求输入，不录入或录入的信息不正确将导致导入失败，红色字段为必填项；\n" +
                " 2、请在表格里面逐行录入数据，建议一次最多导入400条信息；\n" +
                " 3、请不要随意复制单元格，这样会破坏字段规则校验；\n" +
                " 4、租客名+证件号码将用于匹配现有租客的信息，若两个字段完全一致时，则不会导入新增该租客，仅对租客信息进行更新；\n" +
                " 5、若已存在证件号码但租客名不一致，则无法导入；\n" +
                " 6、同一租客名+证件号码不允许重复，重复只算一条租客数据；\n" +
                " 7、若字段为空，导入成功后将不会覆盖系统中对应租客该字段的信息；\n");
        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.setWrapText(true);//设置自动换行
        //设置垂直对齐的样式为居中对齐;
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        style1.setFillForegroundColor((short) 13);// 设置背景色
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFFont font1 = workbook.createFont();
        font1.setBold(true);//粗体
        font1.setFontName("微软雅黑");//设置字体名称
        font1.setFontHeightInPoints((short) 8);//设置字号
        style1.setFont(font1);
        cell1.setCellStyle(style1);
        row1.setHeightInPoints(133);//设置行高
        //合并
        CellRangeAddress region1 = new CellRangeAddress(1, // first row
                1, // last row
                0, // first column
                titleCNName.length - 1 // last column
        );
        sheet.addMergedRegion(region1);
        //第三行
        HSSFCellStyle style3 = workbook.createCellStyle();
        //设置水平对齐的样式为居中对齐;
        style3.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style3.setVerticalAlignment(VerticalAlignment.CENTER);
        style3.setWrapText(true);//设置自动换行
        HSSFCellStyle style4 = workbook.createCellStyle();
        //设置水平对齐的样式为居中对齐;
        style4.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        style4.setVerticalAlignment(VerticalAlignment.CENTER);
        style4.setWrapText(true);//设置自动换行
        HSSFFont font3 = workbook.createFont();
        font3.setBold(true);//粗体
        font3.setFontName("Microsoft YaHei Light");//设置字体名称
        font3.setFontHeightInPoints((short) 12);//设置字号
        font3.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        style3.setFont(font3);
        HSSFFont font4 = workbook.createFont();
        font4.setBold(true);//粗体
        font4.setFontName("Microsoft YaHei Light");//设置字体名称
        font4.setFontHeightInPoints((short) 12);//设置字号
        font4.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style4.setFont(font4);
        //style3.setFont(font3);

        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell2;
        row2.setHeightInPoints(20);//设置行高
        //创建绘图对象
        HSSFPatriarch p = sheet.createDrawingPatriarch();

        //设置列宽
        sheet.setColumnWidth(0, 5400); //第一个参数代表列id(从0开始),第2个参数代表宽度值
        //设置标题名
        cell2 = row2.createCell(0);
        cell2.setCellValue(titleCNName[0]);
        cell2.setCellStyle(style3);
        //设置列宽
        sheet.setColumnWidth(1, 10800); //第一个参数代表列id(从0开始),第2个参数代表宽度值
        //设置标题名
        cell2 = row2.createCell(1);
        cell2.setCellValue(titleCNName[1]);
        cell2.setCellStyle(style3);
        //设置列宽
        sheet.setColumnWidth(2, 8000); //第一个参数代表列id(从0开始),第2个参数代表宽度值
        //设置标题名
        cell2 = row2.createCell(2);
        cell2.setCellValue(titleCNName[2]);
        cell2.setCellStyle(style4);
        //设置列宽
        sheet.setColumnWidth(3, 10800); //第一个参数代表列id(从0开始),第2个参数代表宽度值
        //设置标题名
        cell2 = row2.createCell(3);
        cell2.setCellValue(titleCNName[3]);
        cell2.setCellStyle(style4);
        //设置列宽
        sheet.setColumnWidth(4, 10800); //第一个参数代表列id(从0开始),第2个参数代表宽度值
        //设置标题名
        cell2 = row2.createCell(4);
        cell2.setCellValue(titleCNName[4]);
        cell2.setCellStyle(style4);

        //设置区域范围， 参数：firstRow,lastRow,firstCol,lastCol
        //房源性质区域选择
        CellRangeAddressList cellRangeRoomNatureList = new CellRangeAddressList(3, XLS_MAX_ROW, 4, 4);
        //租赁状态区域选择
        DataValidationHelper dataValidationHelper = sheet.getDataValidationHelper();
        // 构造下拉框和数据
        DataValidationConstraint constraintRoomNature = dataValidationHelper.createExplicitListConstraint(new String[]{"cs", "ds"});
        // 绑定下拉框和区域
        DataValidation validation = dataValidationHelper.createValidation(constraintRoomNature, cellRangeRoomNatureList);
        // 为sheet添加验证
        sheet.addValidationData(validation);

        //如果有错误的数据
        int rowNum = 3;
//        if (errorData != null && errorData.size() > 0) {
//            for (RoomImport errorDatum : errorData) {
//                HSSFRow rowTmp = sheet.createRow(rowNum);
//                for (int i = 0; i < titleCNName.length; i++) {
//                    //设置列宽
//                    sheet.setColumnWidth(i, 3766); //第一个参数代表列id(从0开始),第2个参数代表宽度值
//                    HSSFCell cellTmp = rowTmp.createCell(i);
//                    //设置标题名
//                    switch (i) {
//                        case 0:
//                            cellTmp.setCellValue(errorDatum.getBuildingName());
//                            break;
//                        case 1:
//                            cellTmp.setCellValue(errorDatum.getFloorSerialNumber());
//                            break;
//                        case 2:
//                            cellTmp.setCellValue(errorDatum.getRoomCode());
//                            break;
//                        case 3:
//                            cellTmp.setCellValue(errorDatum.getFloorSpace());
//                            break;
//                        case 4:
//                            cellTmp.setCellValue(errorDatum.getRoomNature());
//                            break;
//                        case 5:
//                            cellTmp.setCellValue(errorDatum.getHireStatus());
//                            break;
//                        default:
//                            break;
//                    }
//                }
//                rowNum++;
//            }
//        }
        workbook.write(new

                File("C:\\Users\\86151\\Desktop\\temp\\s.xls"));
//        return workbook;
    }




}

//package com.zcc.utils;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.enums.WriteDirectionEnum;
//import com.alibaba.excel.write.metadata.WriteSheet;
//import com.alibaba.excel.write.metadata.fill.FillConfig;
//import com.zcc.test.TestDemo;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.junit.Test;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.FileInputStream;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
////
////import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
////import org.apache.poi.ss.usermodel.HorizontalAlignment;
////import org.apache.poi.ss.usermodel.VerticalAlignment;
////import org.apache.poi.ss.util.CellRangeAddress;
////import org.apache.poi.xssf.usermodel.*;
////
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import java.io.IOException;
////import java.io.UnsupportedEncodingException;
////import java.lang.reflect.Method;
////import java.net.URLEncoder;
////import java.nio.charset.StandardCharsets;
////import java.util.List;
////
/////**
//// * @author zcc
//// * @version 1.0
//// * @date 2021/8/25 17:07
//// */
//public class ExcelUtil {
//
//    private String templateFileName = "C:\\Users\\86151\\Desktop\\cs\\label.xlsx";
//    private String fileName = "C:\\Users\\86151\\Desktop\\cs\\demo.xlsx";
//
//    public void excel(HttpServletResponse response) {
//        try {
//            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("E:/file/Excel导出模板.xls"));
//
//// 根据页面index 获取sheet页
//
//            HSSFSheet sheet = wb.getSheet("Sheet1");
//
//            for (int i = 0; i < 100; i++) {
//
//// 创建HSSFRow对象
//
//                HSSFRow row = sheet.createRow(i + 1);
//
//// 创建HSSFCell对象 设置单元格的值
//
//                row.createCell(0).setCellValue("张三" + i);
//
//                row.createCell(1).setCellValue(i);
//
//                row.createCell(2).setCellValue("男" + i);
//
//                row.createCell(3).setCellValue("科研" + i);
//
//            }
//
//// 输出Excel文件
//
//            OutputStream output = response.getOutputStream();
//
//            response.reset();
//
//// 设置文件头
//
//            response.setHeader("Content-Disposition",
//
//                    "attchement;filename=" + new String("人员信息.xls".getBytes("gb2312"), "ISO8859-1"));
//
//            response.setContentType("application/msexcel");
//
//            wb.write(output);
//
//            wb.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void cs() {
//        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
//        // 填充list 的时候还要注意 模板中{.} 多了个点 表示list
//
//
//        List<TestDemo> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            TestDemo demo = new TestDemo();
//            demo.setItem(i + "");
//            demo.setCount(i + "");
//            list.add(demo);
//        }
//        // 方案1 一下子全部放到内存里面 并填充
//        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
//        // 方案2 分多次 填充 会使用文件缓存（省内存）
////            fileName = TestFileUtil.getPath() + "listFill" + System.currentTimeMillis() + ".xlsx";
////            ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
////            WriteSheet writeSheet = EasyExcel.writerSheet().build();
////            excelWriter.fill(data(), writeSheet);
////            excelWriter.fill(data(), writeSheet);
////            // 千万别忘记关闭流
////            excelWriter.finish();
//        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(list);
//    }
//
//    @Test
//    public void createFileByTemplate() {
//        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
////        if(object != null){
//        TestDemo demo = new TestDemo();
//        demo.setItem("sadad" + "");
//        demo.setCount(1 + "");
//        excelWriter.fill(demo, fillConfig, writeSheet);
////        }
//
////        if(!CollectionUtils.isEmpty(list)){
////            excelWriter.fill(list, fillConfig, writeSheet);
////        }
//        excelWriter.finish();
//    }
//
//    /**
//     * 最简单的填充
//     *
//     * @since 2.1.1
//     */
//    @Test
//    public void simpleFill() {
//        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
////            String templateFileName =
////                    TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "simple.xlsx";
//
//        // 方案1 根据对象填充
////            String fileName = TestFileUtil.getPath() + "simpleFill" + System.currentTimeMillis() + ".xlsx";
//        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
//        TestDemo demo = new TestDemo();
//        demo.setItem("sadad" + "");
//        demo.setCount(1 + "");
//        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(demo);
//
//        // 方案2 根据Map填充
////            fileName = TestFileUtil.getPath() + "simpleFill" + System.currentTimeMillis() + ".xlsx";
//        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
////            Map<String, Object> map = new HashMap<String, Object>();
////            map.put("item", "张三");
////            map.put("count", 5.2);
////            EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);
//    }
//
//    /**
//     * 填充列表
//     *
//     * @since 2.1.1
//     */
//    @Test
//    public void listFill() {
//        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
//        // 填充list 的时候还要注意 模板中{.} 多了个点 表示list
////            String templateFileName =
////                    TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "list.xlsx";
////
////            // 方案1 一下子全部放到内存里面 并填充
////            String fileName = TestFileUtil.getPath() + "listFill" + System.currentTimeMillis() + ".xlsx";
////            // 这里 会填充到第一个sheet， 然后文件流会自动关闭
////            EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(data());
////
////            // 方案2 分多次 填充 会使用文件缓存（省内存）
////            fileName = TestFileUtil.getPath() + "listFill" + System.currentTimeMillis() + ".xlsx";
//        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        excelWriter.fill(data(), writeSheet);
//        excelWriter.fill(data(), writeSheet);
//        // 千万别忘记关闭流
//        excelWriter.finish();
//    }
//
//    /**
//     * 复杂的填充
//     *
//     * @since 2.1.1
//     */
//    @Test
//    public void complexFill() {
//        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
//        // {} 代表普通变量 {.} 代表是list的变量
////            String templateFileName =
////                    TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "complex.xlsx";
////
////            String fileName = TestFileUtil.getPath() + "complexFill" + System.currentTimeMillis() + ".xlsx";
//        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
//        // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
//        // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
//        // 如果数据量大 list不是最后一行 参照下一个
//        FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
//        excelWriter.fill(data(), fillConfig, writeSheet);
//        excelWriter.fill(data(), fillConfig, writeSheet);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("date", "2019年10月9日13:28:28");
//        map.put("total", 1000);
//        excelWriter.fill(map, writeSheet);
//        excelWriter.finish();
//    }
//
//    /**
//     * 数据量大的复杂填充
//     * <p>
//     * 这里的解决方案是 确保模板list为最后一行，然后再拼接table.还有03版没救，只能刚正面加内存。
//     *
//     * @since 2.1.1
//     */
//    @Test
//    public void complexFillWithTable() {
//        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
//        // {} 代表普通变量 {.} 代表是list的变量
////            // 这里模板 删除了list以后的数据，也就是统计的这一行
////            String templateFileName =
////                    TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "complexFillWithTable.xlsx";
////
////            String fileName = TestFileUtil.getPath() + "complexFillWithTable" + System.currentTimeMillis() + ".xlsx";
//        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        // 直接写入数据
//        excelWriter.fill(data(), writeSheet);
//        excelWriter.fill(data(), writeSheet);
//
//        // 写入list之前的数据
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("date", "2019年10月9日13:28:28");
//        excelWriter.fill(map, writeSheet);
//
//        // list 后面还有个统计 想办法手动写入
//        // 这里偷懒直接用list 也可以用对象
//        List<List<String>> totalListList = new ArrayList<List<String>>();
//        List<String> totalList = new ArrayList<String>();
//        totalListList.add(totalList);
//        totalList.add(null);
//        totalList.add(null);
//        totalList.add(null);
//        // 第四列
//        totalList.add("统计:1000");
//        // 这里是write 别和fill 搞错了
//        excelWriter.write(totalListList, writeSheet);
//        excelWriter.finish();
//        // 总体上写法比较复杂 但是也没有想到好的版本 异步的去写入excel 不支持行的删除和移动，也不支持备注这种的写入，所以也排除了可以
//        // 新建一个 然后一点点复制过来的方案，最后导致list需要新增行的时候，后面的列的数据没法后移，后续会继续想想解决方案
//    }
//
//    /**
//     * 横向的填充
//     *
//     * @since 2.1.1
//     */
//    @Test
//    public void horizontalFill() {
//        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
//        // {} 代表普通变量 {.} 代表是list的变量
////            String templateFileName =
////                    TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "horizontal.xlsx";
////
////            String fileName = TestFileUtil.getPath() + "horizontalFill" + System.currentTimeMillis() + ".xlsx";
//        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet().build();
//        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
//        excelWriter.fill(data(), fillConfig, writeSheet);
//        excelWriter.fill(data(), fillConfig, writeSheet);
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("date", "2019年10月9日13:28:28");
//        excelWriter.fill(map, writeSheet);
//
//        // 别忘记关闭流
//        excelWriter.finish();
//    }
//
//    /**
//     * 多列表组合填充填充
//     *
//     * @since 2.2.0-beta1
//     */
//    @Test
//    public void compositeFill() {
////            // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
////            // {} 代表普通变量 {.} 代表是list的变量 {前缀.} 前缀可以区分不同的list
////            String templateFileName =
////                    TestFileUtil.getPath() + "demo" + File.separator + "fill" + File.separator + "composite.xlsx";
////
////            String fileName = TestFileUtil.getPath() + "compositeFill" + System.currentTimeMillis() + ".xlsx";
////            ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
////            WriteSheet writeSheet = EasyExcel.writerSheet().build();
////            FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
////            // 如果有多个list 模板上必须有{前缀.} 这里的前缀就是 data1，然后多个list必须用 FillWrapper包裹
////            excelWriter.fill(new FillWrapper("data1", data()), fillConfig, writeSheet);
////            excelWriter.fill(new FillWrapper("data1", data()), fillConfig, writeSheet);
////            excelWriter.fill(new FillWrapper("data2", data()), writeSheet);
////            excelWriter.fill(new FillWrapper("data2", data()), writeSheet);
////            excelWriter.fill(new FillWrapper("data3", data()), writeSheet);
////            excelWriter.fill(new FillWrapper("data3", data()), writeSheet);
////
////            Map<String, Object> map = new HashMap<String, Object>();
////            map.put("date", "2019年10月9日13:28:28");
////            excelWriter.fill(map, writeSheet);
////
////            // 别忘记关闭流
////            excelWriter.finish();
//    }
//
//    private List<TestDemo> data() {
//        List<TestDemo> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            TestDemo demo = new TestDemo();
//            demo.setItem(i + "");
//            demo.setCount(i + "");
//            list.add(demo);
//        }
//        return list;
//    }
//}
//
//
//// 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
//// 填充list 的时候还要注意 模板中{.} 多了个点 表示list
//
//
//// 方案2 分多次 填充 会使用文件缓存（省内存）
////        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
////        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("用户-模板导出.xlsx", "utf-8"));
////        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), User.class).withTemplate(templateInputStream).build();
////        WriteSheet writeSheet = EasyExcel.writerSheet().build();
////        excelWriter.fill(list1, writeSheet);
////        excelWriter.fill(list2, writeSheet);
////        // 千万别忘记关闭流
////        excelWriter.finish();
////    }
//
//
////
////    // 费用类型名称
////    private static final String[] PAYCONFIGNAME = {"","租金","物业费","租金保证金","物业费保证金"};
////    //费用明细 下载表头
////    private static final String [] HEADER = {"租赁合同详情-租赁费用明细报表","保证金总计(人民币CNY)","费用总计(人民币CNY)","物业保证金总计(人民币CNY)","物业费用总计(人民币CNY)",
////            "开始日期","结束日期","付款日","最终单价","单位","应收","费用类型"};
////    // 反射方法
////    private static final String [] METHODS = {"StartTime","EndTime","PayTime","Price","Unit","TotalAmount","PayConfigName"};
////
////    public void downloadBillDetal( HttpServletRequest request, HttpServletResponse response,String companyId, String contractId) throws IOException {
////        try {
//////            HttpServletRequest request = ServletUtils.getRequest();
//////            HttpServletResponse response = ServletUtils.getResponse();
////
////            XSSFWorkbook wb = new XSSFWorkbook();
////            XSSFSheet sheet = wb.createSheet();
////            XSSFCellStyle cellStyle = wb.createCellStyle();
////            // 居中对齐
////            // 设置单元格内容水平对齐
////            cellStyle.setAlignment(HorizontalAlignment.forInt(2));
////            // 设置单元格内容垂直对齐
////            cellStyle.setVerticalAlignment(VerticalAlignment.forInt(1));
////            // 设置自动换行
////            cellStyle.setWrapText(true);
////
////
////            // 右对齐
////            XSSFCellStyle cellStyleRight = wb.createCellStyle();
////            // 设置单元格内容右对齐
////            cellStyleRight.setAlignment(HorizontalAlignment.RIGHT);
////            // 设置单元格内容垂直对齐
////            cellStyleRight.setVerticalAlignment(VerticalAlignment.forInt(1));
////            // 设置自动换行
////            cellStyleRight.setWrapText(true);
////
////
////            // 左对齐
////            XSSFCellStyle cellStyleLeft = wb.createCellStyle();
////            // 设置单元格内容左对齐
////            cellStyleLeft.setAlignment(HorizontalAlignment.LEFT);
////            // 设置单元格内容垂直对齐
////            cellStyleLeft.setVerticalAlignment(VerticalAlignment.forInt(1));
////            // 设置自动换行
////            cellStyleLeft.setWrapText(true);
////
////
////
////            XSSFFont font = wb.createFont();
//////        font.setBoldweight(700);
////            font.setFontName("方正仿宋_GBK");
////            font.setFontHeightInPoints((short) 12);
////            cellStyle.setFont(font);
////
////            BillingDetailsVO billDetail = findBillDetail(companyId, contractId);
////
////            XSSFRow row = sheet.createRow(0);
////            XSSFCell cell = row.createCell(0);
////            cell.setCellValue(HEADER[0]);
////            cell.setCellStyle(cellStyle);
////            sheet.addMergedRegion(new CellRangeAddress(0,0,0,METHODS.length - 1));
////            row.setHeight((short)(30*20)); // 行高 30 像素
////            int rowTotals = 0;
////
////            if (billDetail != null && billDetail.getLeaseBill() != null && (CollectionUtils.isNotEmpty(billDetail.getLeaseBill().getLeaseAmountList()) || CollectionUtils.isNotEmpty(billDetail.getLeaseBill().getLeaseDepositList()))) {
////
////                // 租赁表头
////                XSSFRow row1 = sheet.createRow(1);
////
////                for (int i = 1; i < 3; i++) {
////                    XSSFCell cell1 = row1.createCell(i - 1);
////                    cell1.setCellValue(HEADER[i]);
////                    cell1.setCellStyle(cellStyleLeft);
////                    sheet.setColumnWidth(i - 1, 25 * 256);
////                }
////                // 租赁保证金总和
////                XSSFRow row2 = sheet.createRow(2);
////                XSSFCell cell1 = row2.createCell(0);
////                cell1.setCellValue(billDetail.getLeaseBill().getBond()+"元");
////                cell1.setCellStyle(cellStyleRight);
////
////                // 租赁租金总和
////                XSSFCell cell2 = row2.createCell(1);
////                cell2.setCellValue(billDetail.getLeaseBill().getAmount()+"元");
////                cell2.setCellStyle(cellStyleRight);
////
////                // 账单表头
////                XSSFRow row3 = sheet.createRow(3);
////
////                for (int i = 5; i < HEADER.length; i++) {
////                    XSSFCell cells = row3.createCell(i - 5);
////                    cells.setCellValue(HEADER[i]);
////                    cells.setCellStyle(cellStyleLeft);
////                    sheet.setColumnWidth(i - 5, 25 * 256);
////                }
////                // 数据
////                int rowTotal = 3;
////                List<LeaseDepositListVO> depositList = billDetail.getLeaseBill().getLeaseDepositList();
////                if (CollectionUtils.isNotEmpty(depositList)) {
////                    for (int i = 0; i < depositList.size(); i++) {
////                        rowTotal += 1;
////                        XSSFRow rows = sheet.createRow(rowTotal);
////                        LeaseDepositListVO depositListVO = depositList.get(i);
////                        Class<? extends LeaseDepositListVO> aClass = depositListVO.getClass();
////                        for (int j = 0; j < METHODS.length; j++) {
////                            XSSFCell cells = rows.createCell(j);
////                            if (j == 3 || j == 4) {
////                                cells.setCellValue("--");
////                                cells.setCellStyle(cellStyle);
////                                continue;
////                            }
////                            String methods = "get" + METHODS[j];
////                            Method method = aClass.getMethod(methods);
////                            Object invoke = method.invoke(depositListVO);
////                            if(j == 5) {
////                                cells.setCellValue(invoke == null ? "" : invoke.toString()+"元");
////                                cells.setCellStyle(cellStyleRight);
////                            }
////                            else {
////                                cells.setCellStyle(cellStyleLeft);
////                                cells.setCellValue(invoke == null ? "" : invoke.toString());
////                            }
////                        }
////                    }
////                }
////                List<LeaseAmountListVO> amountList = billDetail.getLeaseBill().getLeaseAmountList();
////                if (CollectionUtils.isNotEmpty(amountList)) {
////                    for (int i = 0; i < amountList.size(); i++) {
////                        rowTotal += 1;
////                        XSSFRow rows = sheet.createRow(rowTotal);
////                        LeaseAmountListVO amountListVO = amountList.get(i);
////                        Class<? extends LeaseAmountListVO> aClass = amountListVO.getClass();
////                        for (int j = 0; j < METHODS.length; j++) {
////                            XSSFCell cells = rows.createCell(j);
////                            String methods = "get" + METHODS[j];
////                            Method method = aClass.getMethod(methods);
////                            Object invoke = method.invoke(amountListVO);
////
////                            if(j == 3 || j == 5) {
////                                if(j == 5)
////                                    cells.setCellValue(invoke == null ? "--" : invoke.toString()+"元");
////                                else
////                                    cells.setCellValue(invoke == null ? "--" : invoke.toString());
////                                cells.setCellStyle(cellStyleRight);
////                            }
////                            else {
////                                cells.setCellValue(invoke == null ? "--" : invoke.toString());
////                                cells.setCellStyle(cellStyleLeft);
////                            }
////                        }
////                        if(CollectionUtils.isNotEmpty(amountListVO.getLeaseAmountChildList())){
////                            List<LeaseAmountChildListVO> childList = amountListVO.getLeaseAmountChildList();
////                            for (int k = 0; k < childList.size(); k++) {
////                                rowTotal += 1;
////                                XSSFRow childRows = sheet.createRow(rowTotal);
////                                LeaseAmountChildListVO child = childList.get(k);
////                                Class<? extends LeaseAmountChildListVO> childClass = child.getClass();
////                                for (int j = 0; j < METHODS.length; j++) {
////                                    XSSFCell cells = childRows.createCell(j);
////                                    if(j == 6){
////                                        cells.setCellValue(amountListVO.getPayConfigName());
////                                    }
////                                    else if(j==2){
////                                        cells.setCellValue(amountListVO.getPayTime());
////                                    }
////                                    else {
////                                        String methods = "get" + METHODS[j];
////                                        Method method = childClass.getMethod(methods);
////                                        Object invoke = method.invoke(child);
////                                        if(j == 5)
////                                            cells.setCellValue(invoke == null ? "--" : invoke.toString()+"元");
////                                        else
////                                            cells.setCellValue(invoke == null ? "--" : invoke.toString());
////                                    }
////                                    if(j == 3 || j == 5)
////                                        cells.setCellStyle(cellStyleRight);
////                                    else
////                                        cells.setCellStyle(cellStyleLeft);
////                                }
////
////                            }
////                        }
////                    }
////                }
////                rowTotals = rowTotal;
////            }
////
////            if (billDetail != null && billDetail.getPropertyBill() != null && (CollectionUtils.isNotEmpty(billDetail.getPropertyBill().getPropertyAmountList()) || CollectionUtils.isNotEmpty(billDetail.getPropertyBill().getPropertyDepositList()))) {
////                // 物业表头
////                rowTotals += 1;
////                XSSFRow row1 = sheet.createRow(rowTotals);
////
////                for (int i = 3; i < 5; i++) {
////                    XSSFCell cell1 = row1.createCell(i - 3);
////                    cell1.setCellValue(HEADER[i]);
////                    cell1.setCellStyle(cellStyleLeft);
////                    sheet.setColumnWidth(i - 3, 25 * 256);
////                }
////                rowTotals += 1;
////                // 物业保证金总和
////                XSSFRow row2 = sheet.createRow(rowTotals);
////                XSSFCell cell1 = row2.createCell(0);
////                cell1.setCellValue(billDetail.getPropertyBill().getBond()+"元");
////                cell1.setCellStyle(cellStyleRight);
////
////                // 物业租金总和
////                XSSFCell cell2 = row2.createCell(1);
////                cell2.setCellValue(billDetail.getPropertyBill().getAmount()+"元");
////                cell2.setCellStyle(cellStyleRight);
////
////                rowTotals += 1;
////                // 账单表头
////                XSSFRow row3 = sheet.createRow(rowTotals);
////
////                for (int i = 5; i < HEADER.length; i++) {
////                    XSSFCell cells = row3.createCell(i - 5);
////                    cells.setCellValue(HEADER[i]);
////                    cells.setCellStyle(cellStyleLeft);
////                    sheet.setColumnWidth(i - 5, 25 * 256);
////                }
////                // 数据
////                List<PropertyDepositListVO> depositList = billDetail.getPropertyBill().getPropertyDepositList();
////                if (CollectionUtils.isNotEmpty(depositList)) {
////                    for (int i = 0; i < depositList.size(); i++) {
////                        rowTotals += 1;
////                        XSSFRow rows = sheet.createRow(rowTotals);
////                        PropertyDepositListVO depositListVO = depositList.get(i);
////                        Class<? extends PropertyDepositListVO> aClass = depositListVO.getClass();
////                        for (int j = 0; j < METHODS.length; j++) {
////                            XSSFCell cells = rows.createCell(j);
////                            if (j == 3 || j == 4) {
////                                cells.setCellValue("--");
////                                cells.setCellStyle(cellStyle);
////                                continue;
////                            }
////                            String methods = "get" + METHODS[j];
////                            Method method = aClass.getMethod(methods);
////                            Object invoke = method.invoke(depositListVO);
////
////                            if(j == 5) {
////                                cells.setCellValue(invoke == null ? "" : invoke.toString()+"元");
////                                cells.setCellStyle(cellStyleRight);
////                            }
////                            else {
////                                cells.setCellValue(invoke == null ? "" : invoke.toString());
////                                cells.setCellStyle(cellStyleLeft);
////                            }
////                        }
////                    }
////                }
////                List<PropertyAmountListVO> amountList = billDetail.getPropertyBill().getPropertyAmountList();
////                if (CollectionUtils.isNotEmpty(amountList)) {
////                    for (int i = 0; i < amountList.size(); i++) {
////                        rowTotals += 1;
////                        XSSFRow rows = sheet.createRow(rowTotals);
////                        PropertyAmountListVO amountListVO = amountList.get(i);
////                        Class<? extends PropertyAmountListVO> aClass = amountListVO.getClass();
////                        for (int j = 0; j < METHODS.length; j++) {
////                            XSSFCell cells = rows.createCell(j);
////                            String methods = "get" + METHODS[j];
////                            Method method = aClass.getMethod(methods);
////                            Object invoke = method.invoke(amountListVO);
////
////                            if(j == 3 || j == 5) {
////                                if(j == 5)
////                                    cells.setCellValue(invoke == null ? "--" : invoke.toString()+"元");
////                                else
////                                    cells.setCellValue(invoke == null ? "--" : invoke.toString());
////                                cells.setCellStyle(cellStyleRight);
////                            }
////                            else {
////                                cells.setCellValue(invoke == null ? "--" : invoke.toString());
////                                cells.setCellStyle(cellStyleLeft);
////                            }
////                        }
////                        if(CollectionUtils.isNotEmpty(amountListVO.getPropertyAmountChildList())){
////                            List<PropertyAmountChildListVO> childList = amountListVO.getPropertyAmountChildList();
////                            for (int k = 0; k < childList.size(); k++) {
////                                rowTotals += 1;
////                                XSSFRow childRows = sheet.createRow(rowTotals);
////                                PropertyAmountChildListVO child = childList.get(k);
////                                Class<? extends PropertyAmountChildListVO> childClass = child.getClass();
////                                for (int j = 0; j < METHODS.length; j++) {
////                                    XSSFCell cells = childRows.createCell(j);
////                                    if(j == 6){
////                                        cells.setCellValue(amountListVO.getPayConfigName());
////                                    }
////                                    else if(j == 2) {
////                                        cells.setCellValue(amountListVO.getPayTime());
////                                    }
////                                    else {
////                                        String methods = "get" + METHODS[j];
////                                        Method method = childClass.getMethod(methods);
////                                        Object invoke = method.invoke(child);
////                                        if(j == 5)
////                                            cells.setCellValue(invoke == null ? "--" : invoke.toString()+"元");
////                                        else
////                                            cells.setCellValue(invoke == null ? "--" : invoke.toString());
////                                    }
////                                    if(j == 3 || j == 5)
////                                        cells.setCellStyle(cellStyleRight);
////                                    else
////                                        cells.setCellStyle(cellStyleLeft);
////
////                                }
////                            }
////                        }
////                    }
////                }
////
////            }
////            String result = downloadExcel(request, response, wb, "租赁合同详情-租金明细报表");
//////            return result;
////        }catch (Exception e){
////            e.printStackTrace();
////            log.error("账单明细导出IO异常",e);
////            throw new IOException(e);
////        }
////    }
////    private static String downloadExcel(HttpServletRequest request, HttpServletResponse response, XSSFWorkbook workbook, String fileName) throws IOException {
////        // 设置响应和请求编码utf-8
////        request.setCharacterEncoding("UTF-8");
////        response.setCharacterEncoding("UTF-8");
////        String result = "导出失败";
////
////        String strFileName;
////        strFileName = fileName + ".xlsx";// Excel名称
////        response = setResponseHeader(request, response, strFileName);
////        response.flushBuffer();// 刷新缓冲
////        try {
////            workbook.write(response.getOutputStream());
////            result = "导出成功";
////        } catch (IOException e) {
////            e.printStackTrace();
//////            log.error(e.getMessage());
////
////            result = "导出失败";
////        }finally {
////            response.getOutputStream().close();
////        }
////        return result;
////    }
////
////    private static HttpServletResponse setResponseHeader(HttpServletRequest request, HttpServletResponse response,
////                                                         String strFileName) throws UnsupportedEncodingException {
////        response.setContentType("application/octet-stream; charset=utf-8");
////        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
////            response.setHeader("Content-Disposition",
////                    "attachment; filename=" + new String(strFileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));// firefox浏览器
////        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
////            response.setHeader("Content-Disposition",
////                    "attachment; filename=" + URLEncoder.encode(strFileName, "UTF-8"));// IE浏览器
////        } else {
////            response.setHeader("Content-Disposition",
////                    "attachment; filename=" + new String(strFileName.getBytes("gb2312"), "ISO8859-1"));
////        }
////        return response;
////    }
////
//

package com.zcc.utils.excelExport.v2;


import com.zcc.utils.excelExport.v1.GetAnnotationValue;
import com.zcc.utils.sqlBuilder.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/8/31 13:58
 */


@Slf4j
public abstract class POIExcelExportUtils<T> {

    private Type type;
    private Class<T> clazz;
    private String[] fields;
    private String[] names;

    public POIExcelExportUtils() {
        // 获取泛型类型
        Type superClass = getClass().getGenericSuperclass();
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        if (this.type instanceof ParameterizedType) {
            this.clazz = (Class<T>) ((ParameterizedType) this.type).getRawType();
        } else {
            this.clazz = (Class<T>) this.type;
        }
        // 获取注解值
        Map<String, Map<String, String>> fieldAnnotationValue = null;
        try {
            fieldAnnotationValue = GetAnnotationValue.getFieldAnnotationValue(clazz, AnnotationExport.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        Set<Map.Entry<String, Map<String, String>>> entries = fieldAnnotationValue.entrySet();
        fields = new String[fieldAnnotationValue.size()];
        names = new String[fieldAnnotationValue.size()];
        for (int i = 0; i < fields.length; i++) {
            for (Map.Entry<String, Map<String, String>> entry : entries) {
                if (entry.getValue().get("index").equals(i + "")) {
                    fields[i] = "get" + StringUtils.convertToCamelCase(entry.getKey());
                    names[i] = entry.getValue().get("columnName");
                }
            }
        }
    }

//    @SuppressWarnings("unchecked")
//    public void init() throws Exception { // 获取泛型类型
//        Type superClass = getClass().getGenericSuperclass();
//        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
//        if (this.type instanceof ParameterizedType) {
//            this.clazz = (Class<T>) ((ParameterizedType) this.type).getRawType();
//        } else {
//            this.clazz = (Class<T>) this.type;
//        }
//        Map<String, Map<String, String>> fieldAnnotationValue = GetAnnotationValue.getFieldAnnotationValue(clazz, AnnotationExport.class);
//        Set<Map.Entry<String, Map<String, String>>> entries = fieldAnnotationValue.entrySet();
//        fields = new String[fieldAnnotationValue.size()];
//        boolean flag = true;
//        for (int i = 0; i < fields.length; i++) {
//            for (Map.Entry<String, Map<String, String>> entry : entries) {
//                if (entry.getValue().get("index").equals(i+"")) {
//                    fields[i] = "get"+ StringUtils.convertToCamelCase(entry.getKey());
//                    flag = !flag;
//                    continue;
//                }
////                if (!flag){
////                if (entry.getValue().get("columnName").equals(columnList.get(i))){
////                    fields[i] = "get" + StringUtils.convertToCamelCase(entry.getKey());
////                        flag = !flag;
////                }
////                }
//            }
//
//        }
//
//    }


    public void exportData(HttpServletRequest request, List<T> data, String sheetName, String name, HttpServletResponse response, Map<String, String> map) throws Exception {
        try {
            //创建工作簿
            XSSFWorkbook workBook = new XSSFWorkbook();
            //创建工作表sheet
            XSSFSheet sheet = workBook.createSheet(sheetName);

            /**
             * 第1行 标题
             */
            XSSFRow row0 = sheet.createRow(0);

//            设置行高
//            row0.setHeight((short) 300);
//            获取单元格样式
            XSSFCellStyle cellStyle0 = getXSSFCellStyle(workBook);

            //创建标题单元格
            XSSFCell cell0 = row0.createCell(0);
            cell0.setCellStyle(cellStyle0);
            cell0.setCellValue(sheetName);
            //合并标题单元格，行和列都是从0开始计数，4个参数，分别为起始行，结束行，起始列，结束列
            CellRangeAddress region0 = new CellRangeAddress(0, 0, 0, names.length - 1);
            sheet.addMergedRegion(region0);
            // 第2行 表头
            XSSFRow row = sheet.createRow(1);
            for (int columnIndex = 0; columnIndex < names.length; columnIndex++) {
                XSSFCell cell = row.createCell(columnIndex);
                XSSFCellStyle cellStyle = getXSSFCellStyle(workBook);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(names[columnIndex]);
                sheet.setColumnWidth(columnIndex, 5000);
            }
            int i = 1;
            if (CollectionUtils.isNotEmpty(data)) {
                for (T item : data) {
                    XSSFRow xssfRow = sheet.createRow(i + 1);
                    int j = 0;
                    for (; j < fields.length; j++) {
                        XSSFCell xssfCell = xssfRow.createCell(j);
                        xssfCell.setCellStyle(cellStyle0);
                        Method method = clazz.getMethod(fields[j]);
                        Object obj = method.invoke(item);
                        if (Objects.isNull(obj)) {
                            obj = "";
                        }
                        xssfCell.setCellValue(obj.toString());
                    }
                    if (map != null && map.size() > 0) {
                        for (String s : map.keySet()) {
                            XSSFCell xssfCell = xssfRow.createCell(j);
                            xssfCell.setCellStyle(cellStyle0);
                            xssfCell.setCellValue(map.get(s));
                            j++;
                        }
                    }
                    i++;
                }
            }
            downloadExcel(request, response, workBook, name);
        } catch (Exception e) {
            log.error("导出数据表格信息异常：" + e.getMessage(), e);
        }
    }


    /**
     * 将传入的workbook导出
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private static String downloadExcel(HttpServletRequest request, HttpServletResponse response, XSSFWorkbook workbook, String fileName) throws IOException {
        // 设置响应和请求编码utf-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String result = "导出失败";

        String strFileName;
        strFileName = fileName + ".xlsx";// Excel名称
        response = setResponseHeader(request, response, strFileName);
        response.flushBuffer();// 刷新缓冲
        try {
            workbook.write(response.getOutputStream());
            result = "导出成功";
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            result = "导出失败";
        } finally {
            response.getOutputStream().close();
        }
        return result;
    }

    private static HttpServletResponse setResponseHeader(HttpServletRequest request, HttpServletResponse response,
                                                         String strFileName) throws UnsupportedEncodingException {
        response.setContentType("application/octet-stream; charset=utf-8");
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + new String(strFileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1"));// firefox浏览器
        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(strFileName, "UTF-8"));// IE浏览器
        } else {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + new String(strFileName.getBytes("gb2312"), "ISO8859-1"));
        }
        return response;
    }


    private XSSFCellStyle getXSSFCellStyle(XSSFWorkbook workBook) {
        XSSFCellStyle cellStyle = workBook.createCellStyle();
        //单元格内容垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //单元格内容水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
//        //下边框
//        cellStyle.setBorderBottom(BorderStyle.THIN);
//        //左边框
//        cellStyle.setBorderLeft(BorderStyle.THIN);
//        //上边框
//        cellStyle.setBorderTop(BorderStyle.THIN);
//        //右边框
//        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

}



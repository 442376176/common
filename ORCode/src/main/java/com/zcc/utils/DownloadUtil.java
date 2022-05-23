package com.zcc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/1/10 19:24
 */
@Slf4j
public class DownloadUtil {


    public static String downloadFile(HttpServletRequest request, HttpServletResponse response, InputStream stream, String fileName) {
        try {
            response = setResponseHeader(request, response, fileName);
            // 读出文件到response
            // 再把流的内容写到response的输出流供用户下载
            byte[] bytes = toByteArray(stream);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            stream.close();
            outputStream.close();
            return "导出成功";
        } catch (Exception e) {
            log.error("导出文件报错:---------\n" + e.getMessage());
        }
        return "导出失败";

    }

    /**
     * InputStream转化为byte数组
     *
     * @param input
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

    public static String downloadFile(HttpServletRequest request, HttpServletResponse response, File file) {
        try {
            response = setResponseHeader(request, response, file.getName());
            // 读出文件到response
            // 这里是先需要把要把文件内容先读到缓冲区
            // 再把缓冲区的内容写到response的输出流供用户下载
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] b = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(b);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(b);
            // 人走带门
            bufferedInputStream.close();
            outputStream.flush();
            outputStream.close();
            return "导出成功";
        } catch (Exception e) {
            log.error("导出文件报错:---------\n" + e.getMessage());
        }
        return "导出失败";

    }


    /**
     * 将传入的workbook导出
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public static String downloadExcel(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook workbook, String fileName) throws IOException {

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


    /**
     * 设置请求头
     *
     * @param request
     * @param response
     * @param strFileName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static HttpServletResponse setResponseHeader(HttpServletRequest request, HttpServletResponse response,
                                                        String strFileName) throws UnsupportedEncodingException {
        // 设置响应和请求编码utf-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream; charset=utf-8");
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) { // 添加"/"+fileName+"/"转义解决相关问题
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String(strFileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1") + "\"");// firefox浏览器
        } else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode(strFileName, "UTF-8"));// IE浏览器
        } else {
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String(strFileName.getBytes("gb2312"), "ISO8859-1") + "\"");
        }
        return response;
    }

}

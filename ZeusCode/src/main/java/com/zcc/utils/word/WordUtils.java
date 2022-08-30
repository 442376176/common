package com.zcc.utils.word;


import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.CellStyle;
import com.deepoove.poi.data.style.ParagraphStyle;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.deepoove.poi.util.PoitlIOUtils;
import com.deepoove.poi.xwpf.NiceXWPFDocument;
import com.zcc.utils.CollectionUtils;
import com.zcc.utils.Constant;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordUtils {
    private static Configure configure = Configure.builder().buildGramer("${", "}")
            .build();

    /**
     * 根据docx模板文件生成word文档
     *
     * @param wordTemplate   文件
     * @param dataMap        规则字段填写的数据
     * @param outputWordPath 输出的文件路径
     * @param fileName       输出的文件名
     * @param tableNameList  表格变量名
     * @throws Exception
     */
    public static void writeDocxTableFile(String wordTemplate,
                                          Map<String, Object> dataMap, List<String> tableNameList, String outputWordPath, String fileName) throws Exception {
        Configure configureTable;
        ConfigureBuilder configureBuilder = createConfigureBuilder(tableNameList);
        configureTable = configureBuilder.build();
        XWPFTemplate template
                = XWPFTemplate.compile(wordTemplate, configureTable).render(dataMap);
        String outputPath = outputWordPath + fileName;
        template.writeAndClose(new FileOutputStream(outputPath));
    }

    /**
     * 根据docx模板文件生成word文档
     *
     * @param wordTemplate   文件
     * @param dataMap        规则字段填写的数据
     * @param outputWordPath 输出的文件路径
     * @param fileName       输出的文件名
     * @param tableNameList  表格变量名
     * @throws Exception
     */
    public static void writeDocxTableTemplateToFile(String wordTemplate,
                                                    Map<String, Object> dataMap, String outputWordPath, String fileName) throws Exception {
        XWPFTemplate template
                = XWPFTemplate.compile(wordTemplate).render(dataMap);
        String outputPath = outputWordPath + fileName;
        template.writeAndClose(new FileOutputStream(outputPath));
    }

    /**
     * 根据docx格式word模板读取完后返回输入流
     *
     * @param wordTemplate   文件
     * @param dataMap        规则字段填写的数据
     * @param outputWordPath 输出的文件路径
     * @param fileName       输出的文件名
     * @param tableNameList  表格变量名
     * @throws Exception
     */
    public static InputStream writeDocxTableTemplateToInputStream(InputStream stream,
                                                                  Map<String, Object> dataMap) throws Exception {
        XWPFTemplate template
                = XWPFTemplate.compile(stream).render(dataMap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        template.writeAndClose(byteArrayOutputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return inputStream;
    }

    /**
     * 创建配置
     *
     * @param tableNameList
     * @return
     * @throws Exception
     */
    private static ConfigureBuilder createConfigureBuilder(List<String> tableNameList) throws Exception {
        ConfigureBuilder configureBuilder = Configure.builder().buildGramer("${", "}");
        if (CollectionUtils.isNotEmpty(tableNameList)) {
            for (String s : tableNameList) {
                //配置多表格数据
                HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
                configureBuilder.bind(s, policy);
            }
        }
        return configureBuilder;
    }

    /**
     * 根据docx模板文件生成word文档
     *
     * @param wordTemplate   文件
     * @param dataMap        规则字段填写的数据
     * @param outputWordPath 输出的文件路径
     * @param fileName       输出的文件名
     * @throws Exception
     */
    public static void writeDocxWordFile(String wordTemplate,
                                         Object dataMap, String outputWordPath, String fileName) throws Exception {
        XWPFTemplate template
                = XWPFTemplate.compile(wordTemplate).render(dataMap);
        String outputPath = outputWordPath + fileName;
        template.writeAndClose(new FileOutputStream(outputPath));
    }

    /**
     * docx格式word文档读取完后返回输入流
     *
     * @param stream
     * @param dataMap
     * @return
     * @throws Exception
     */
    public static InputStream writeDocxWordInputStream(InputStream stream,
                                                       Map<String, Object> dataMap) throws Exception {
        XWPFTemplate template
                = XWPFTemplate.compile(stream).render(dataMap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        template.writeAndClose(byteArrayOutputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return inputStream;
    }

    /**
     * docx格式word文档读取完后返回输入流
     *
     * @param stream
     * @param dataMap
     * @param tableName 表格名称
     * @return
     * @throws Exception
     */
    public static InputStream writeDocxTableInputStream(InputStream stream,
                                                        Map<String, Object> dataMap, String tableName) throws Exception {
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
        Configure configureTable = Configure.builder().buildGramer("${", "}")
                .bind(tableName, policy).build();
        XWPFTemplate template
                = XWPFTemplate.compile(stream, configureTable).render(dataMap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        template.writeAndClose(byteArrayOutputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return inputStream;
    }


    /**
     * 根据docx模板流生成word文档
     *
     * @param stream   输入流
     * @param dataMap  规则字段填写的数据
     * @param filePath 文件路径
     * @param fileName 输出的文件名
     * @throws Exception
     */
    public static String writeDocxWordFile(InputStream stream,
                                           Map<String, Object> dataMap, String filePath,
                                           String fileName) throws Exception {
        XWPFTemplate template
                = XWPFTemplate.compile(stream).render(dataMap);
        configure.getGrammerRegex();
        String outputPath = filePath + fileName;
        template.writeAndClose(new FileOutputStream(outputPath));
        return outputPath;
    }

    /**
     * 根据docx版本输入流生成输出流
     *
     * @param stream   输入流
     * @param dataMap  规则字段填写的数据
     * @param response http返回结果对象
     * @param fileName 输出的文件名称
     * @throws Exception
     */
    public static void writeDocxWordOutputStream(InputStream stream,
                                                 Map<String, Object> dataMap,
                                                 HttpServletResponse response, String fileName) throws Exception {
        XWPFTemplate template = XWPFTemplate.compile(stream).render(dataMap);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()) + "\"");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        template.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(template, bos, out);
    }

    /**
     * 根据doc格式模板生成文件
     *
     * @param stream
     * @param dataMap
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static String writeDocWordFile(InputStream stream,
                                          Map<String, Object> dataMap, String filePath,
                                          String fileName) throws Exception {
        HWPFDocument document = new HWPFDocument(stream);
        Range range = document.getRange();
        for (Map.Entry entry : dataMap.entrySet()) {
            String key = "${" + entry.getKey() + "}";
            String value = String.valueOf(entry.getValue());
            range.replaceText(key, value);
        }
        String outputPath = filePath + fileName;
        document.write(new FileOutputStream(outputPath));
        return outputPath;
    }


    /**
     * 根据doc格式模板生成文件
     *
     * @param stream
     * @param dataMap
     * @param filePath
     * @param fileName
     * @param tableName
     * @throws Exception
     */
    public static String writeDocTableFile(InputStream stream,
                                           Map<String, Object> dataMap, String tableName, String filePath,
                                           String fileName) throws Exception {
        HWPFDocument document = new HWPFDocument(stream);
        Range range = document.getRange();

        for (Map.Entry entry : dataMap.entrySet()) {
            //表格数据
            if (tableName.equals(entry.getKey())) {
                //将表名替换成空
                range.replaceText("${" + entry.getKey() + "}", "");

                //填充表格数据
                List<Map<String, Object>> tableData = (List) entry.getValue();
                writeTableData(range, tableData, tableName);
                continue;
            }
            String key = "${" + entry.getKey() + "}";
            String value = String.valueOf(entry.getValue());
            range.replaceText(key, value);
        }
        String outputPath = filePath + fileName;
        document.write(new FileOutputStream(outputPath));
        return outputPath;
    }

    /**
     * 表格数据填充
     *
     * @param range
     */
    private static void writeTableData(Range range, List<Map<String, Object>> tableData, String tableName) {
        //遍历range范围内的table。
        if (CollectionUtils.isEmpty(tableData)) {
            return;
        }
        TableIterator tableIter = new TableIterator(range);
//        Map<String,List<String>> cellNameListMap = new HashMap<>();
//        List<String> cellNameList = new ArrayList<>();
        while (tableIter.hasNext()) {
            //老表格
            Table oldTable = tableIter.next();
            //默认取第一行的值
            TableRow oldTableRow = oldTable.getRow(1);
            //记录变量集合
//            for (int i = 0; i < tableData.size(); i++) {
            //取某一行的表格数据
            Map<String, Object> tableRowData = tableData.get(0);
            for (Map.Entry entry : tableRowData.entrySet()) {
                for (int k = 0; k < oldTableRow.numCells(); k++) {
                    TableCell oldTableRowCell = oldTableRow.getCell(k);
                    String key = "[" + entry.getKey() + "]";
                    String value = String.valueOf(entry.getValue());
                    if (key.equals(oldTableRowCell.text().trim())) {
                        //变量匹配成功
                        oldTableRowCell.replaceText(key, value);
//                            cellNameList.add(oldTableRowCell.text().trim());
                        //输出单元格的文本
                        System.out.println(oldTableRowCell.text().trim());
                        break;
                    }
                }
            }
//            }
//            oldTableRow.delete();
            //删除老的表格
//            oldTable.delete();
        }
    }

    /**
     * doc格式word文档读取完后返回输入流
     *
     * @param stream
     * @param dataMap
     * @return
     * @throws Exception
     */
    public static InputStream writeDocWordInputStream(InputStream stream,
                                                      Map<String, Object> dataMap) throws Exception {
        HWPFDocument document = new HWPFDocument(stream);
        Range range = document.getRange();
        for (Map.Entry entry : dataMap.entrySet()) {
            String key = "${" + entry.getKey() + "}";
            String value = String.valueOf(entry.getValue());
            range.replaceText(key, value);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        document.close();
        byteArrayOutputStream.close();
        return inputStream;
    }

    /**
     * @param fileOutputPath
     * @throws Exception
     */
    public static InputStream getFileInputStream(String fileOutputPath) throws Exception {
        File file = new File(fileOutputPath);
        InputStream inputStream = new FileInputStream(file);
        return inputStream;
    }

    /**
     * 将多个word文件合并成一个文件
     *
     * @param inputStreamList
     * @return
     * @throws Exception
     */
    public static InputStream writeDocxWordList(List<InputStream> inputStreamList) throws Exception {
        NiceXWPFDocument mainXWPFDocument = new NiceXWPFDocument(inputStreamList.get(0));
        for (int i = 1; i < inputStreamList.size(); i++) {
            NiceXWPFDocument subTemplate = new NiceXWPFDocument(inputStreamList.get(i));
            mainXWPFDocument = mainXWPFDocument.merge(subTemplate);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mainXWPFDocument.write(byteArrayOutputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        mainXWPFDocument.close();
        byteArrayOutputStream.close();
        return inputStream;
    }

    /**
     * 根据doc模板输入流生成输出流
     *
     * @param stream
     * @param dataMap
     * @param response
     * @param fileName
     * @throws Exception
     */
    public static void writeDocWordOutputStream(InputStream stream,
                                                Map<String, Object> dataMap,
                                                HttpServletResponse response, String fileName) throws Exception {
        HWPFDocument document = new HWPFDocument(stream);
        Range range = document.getRange();
        for (Map.Entry entry : dataMap.entrySet()) {
            String key = "${" + entry.getKey() + "}";
            String value = String.valueOf(entry.getValue());
            range.replaceText(key, value);
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()) + "\"");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        document.write(bos);
        bos.flush();
        out.flush();
        document.close();
        PoitlIOUtils.closeQuietlyMulti(bos, out);
    }

    public static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CellRenderData getCellRenderData() throws Exception {
        CellRenderData cellRenderData = new CellRenderData();
        setCellStyle(cellRenderData, Constant.NumberEnum.ZERO.getNumberStr());
        return cellRenderData;
    }

    /**
     * 设置列样式
     *
     * @param cellRenderData
     * @param style          0 居中
     * @throws Exception
     */
    private static void setCellStyle(CellRenderData cellRenderData, String style) throws Exception {
        CellStyle cellStyle = new CellStyle();
        if (Constant.NumberEnum.ZERO.getNumberStr().equals(style)) {
            cellStyle.setVertAlign(XWPFTableCell.XWPFVertAlign.CENTER);
            cellStyle.setDefaultParagraphStyle(ParagraphStyle.builder().withAlign(ParagraphAlignment.CENTER).build());
        }
        cellRenderData.setCellStyle(cellStyle);
    }

    public static void main(String[] args) throws Exception {
        String wordTemplate = "D:\\building-lease\\file\\合同模板_表格.docx";

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("基本信息_合同编号", "12345678");
        dataMap.put("基本信息_合同租赁面积", "10000平米");
        dataMap.put("基本信息_合同开始日", "2021-05-11");
        dataMap.put("基本信息_租客法人", "2023-05-11");
        //定义表格
        List<RowRenderData> tableDatas = new ArrayList<>();


        //插入表头
        RowRenderData header = new RowRenderData();


        header.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("期数")));
        header.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("租赁期数")));
        header.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("费用类型")));
        header.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("付款日")));
        header.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("租赁数")));
        header.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("最终单价")));
        header.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("最终金额")));
        header.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("小计")));
        tableDatas.add(header);

        //插入表格数据
        for (int i = 1; i < 10; i++) {
            RowRenderData tableRow = new RowRenderData();
            tableRow.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText(String.valueOf(i))));
            tableRow.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText(String.valueOf(i))));
            tableRow.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("学杂费")));
            tableRow.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("2021-12-01")));
            tableRow.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText(String.valueOf(i))));
            tableRow.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("50")));
            tableRow.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("100")));
            tableRow.addCell(getCellRenderData().addParagraph(new ParagraphRenderData().addText("200")));
            tableDatas.add(tableRow);
        }
        //合并单元格规则
        MergeCellRule mergeCellRule = MergeCellRule.builder()
                //第3列的数据合并单元格
                .map(MergeCellRule.Grid.of(1, 2), MergeCellRule.Grid.of(2, 2))
                //第8列的数据合并单元格
                .map(MergeCellRule.Grid.of(1, 7), MergeCellRule.Grid.of(2, 7))
                .build();
        TableRenderData tableRenderData = new TableRenderData();
        tableRenderData.setRows(tableDatas);
        tableRenderData.setMergeRule(mergeCellRule);
        dataMap.put("合同明细表格", tableRenderData);
//        List<Map<String,Object>> tableDataList = new ArrayList<>();
//        for(int i = 0;i<5;i++){
//            Map<String,Object> tableData = new HashMap<>();
//            tableData.put("表格_基本信息_合同编号",i);
//            tableData.put("表格_基本信息_合同租赁面积","20000");
//            tableData.put("表格_基本信息_合同开始日","2021-05-11");
//            tableData.put("表格_基本信息_租客法人","2023-05-23");
//            tableDataList.add(tableData);
//        }
//        dataMap.put("表格_0",tableDataList);
//
//
//        List<Map<String,Object>> tableDataList1 = new ArrayList<>();
//        for(int i = 0;i<5;i++){
//            Map<String,Object> tableData = new HashMap<>();
//            tableData.put("表格_基本信息_合同编号",i);
//            tableData.put("表格_基本信息_合同租赁面积","20000");
//            tableData.put("表格_基本信息_合同开始日","2021-05-11");
//            tableData.put("表格_基本信息_租客法人","2023-05-23");
//            tableDataList1.add(tableData);
//        }
//        dataMap.put("表格_1",tableDataList1);
//
//        List<Map<String,Object>> tableDataList2 = new ArrayList<>();
//        for(int i = 0;i<5;i++){
//            Map<String,Object> tableData = new HashMap<>();
//            tableData.put("表格_基本信息_合同编号",i);
//            tableData.put("表格_基本信息_合同租赁面积","20000");
//            tableData.put("表格_基本信息_合同开始日","2021-05-11");
//            tableData.put("表格_基本信息_租客法人","2023-05-23");
//            tableDataList2.add(tableData);
//        }
//        dataMap.put("表格_2",tableDataList2);

        String outputWordPath = "D:\\building-lease\\file\\";
        String fileName = "新大楼合同-" + System.currentTimeMillis() + ".doc";
        File file = new File(wordTemplate);
        if (file.getName().contains("docx")) {
//            InputStream inputStream = writeDocxWordInputStream(new FileInputStream(wordTemplate),dataMap);

//            ByteArrayOutputStream baos = cloneInputStream(inputStream);
            // 打开两个新的输入流
//            InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
//            InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
//            baos.close();
//            writeDocxWordFile(stream1,new HashMap<>(),outputWordPath,"新大楼合同2.docx");
//            List<String> tableNameList = new ArrayList<>();
//            tableNameList.add("表格_0");
//            tableNameList.add("表格_1");
//            tableNameList.add("表格_2");

//            InputStream inputStream = writeDocxTableTemplateToInputStream(new FileInputStream(wordTemplate),dataMap);
            InputStream inputStream = new FileInputStream(wordTemplate);
            WordWaterMarkUtils.addWaterMark(writeDocxTableTemplateToInputStream(inputStream, dataMap), "北京百度科技有限公司", 50L);
        } else {
//            InputStream inputStream = writeDocWordInputStream(new FileInputStream(wordTemplate),dataMap);
//
//            ByteArrayOutputStream baos = cloneInputStream(inputStream);
//            // 打开两个新的输入流
//            InputStream stream1 = new ByteArrayInputStream(baos.toByteArray());
//            InputStream stream2 = new ByteArrayInputStream(baos.toByteArray());
//            baos.close();
//
//            writeDocxWordFile(stream1,new HashMap<>(),outputWordPath,fileName);
            writeDocTableFile(new FileInputStream(wordTemplate),
                    dataMap, "表格_0", outputWordPath, fileName);
        }


        //生成多个word合并成一个
//        List<InputStream> inputStreamList = new ArrayList<>();
//        //1.获取模板生成通知单 这一步可以通过文件服务接口获取模板输入流 对接周池池
//        File file = new File("D:\\templates\\缴费通知单.docx");
//
//        Map<String,Object> dataMap = new HashMap<>();
//        dataMap.put("tenantName","客户企业");
//        dataMap.put("payTime","10000平米");
//        dataMap.put("needAmount","10000");
//        dataMap.put("capitalNeedAmount","壹万元整");
//        dataMap.put("payTime2","2021-05-31");
//        dataMap.put("noticeTime","2021-05-31");
//        //通过获取的模板组装数据生成通知单
//        InputStream inputStream = writeDocxWordInputStream(new FileInputStream(file),dataMap);
//        inputStreamList.add(inputStream);
//
//        //再次获取模板生成通知单  这一步可以通过文件服务接口获取模板输入流 对接周池池
//        File file2 = new File("D:\\templates\\缴费通知单.docx");
//        Map<String,Object> dataMap2 = new HashMap<>();
//        dataMap2.put("tenantName","客户企业2");
//        dataMap2.put("payTime","2021-06-31");
//        dataMap2.put("needAmount","10000");
//        dataMap2.put("capitalNeedAmount","壹万元整");
//        dataMap2.put("payTime2","2021-06-31");
//        dataMap2.put("noticeTime","2021-06-31");
//        //通过获取的模板组装数据生成通知单
//        InputStream inputStream2 = writeDocxWordInputStream(new FileInputStream(file2),dataMap2);
//        inputStreamList.add(inputStream2);
//
//
//        //将生成好的多个通知单合并成一个
//        writeDocxWordList(inputStreamList);
    }
}
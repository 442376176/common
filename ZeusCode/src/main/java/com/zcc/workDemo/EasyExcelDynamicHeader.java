package com.zcc.workDemo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: building-lease-contract-income
 * @Package: com.eazytec.utils
 * @ClassName: EazyExcelDemo
 * @Author: yangjianfei
 * @Description:
 * @Date: 2022/2/15 10:58
 * @Version: 1.0
 */
public class EasyExcelDynamicHeader {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\86151\\Desktop\\temp\\test.xls";
        List<String> monthList = new ArrayList<>();
        for(int i =1 ;i< 10; i++){
            monthList.add("2022-0"+i);
        }
        //需要合并的列
        List<Integer> columnIndexList = new ArrayList<>();
        int index = 10;
        for(int i =0;i <= index;i++){
            columnIndexList.add(i);
        }
        index = index + monthList.size() +1;
        columnIndexList.add(index+1);
        columnIndexList.add(index+2);
        columnIndexList.add(index+3);
        columnIndexList.add(index+4);
        columnIndexList.add(index+5);

        MergeStrategy loopMergeStrategy = new MergeStrategy(4, columnIndexList);

        //表头
        List<List<String>> createTableHead = createTableHead(monthList);
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(createTableHead)
                .sheet("模板")
                .registerWriteHandler(loopMergeStrategy)
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(data(monthList));
    }

    private static List<List<String>> data( List<String> monthList) {
        List<List<String>> list = ListUtils.newArrayList();
        for (int i = 0; i < 12; i++) {
            List<String> dataList = new ArrayList<>();
            dataList.add("0000");
            dataList.add("新时代大厦");
            dataList.add("新时代大厦");
            dataList.add("新时代大厦");
            dataList.add("新时代大厦");
            dataList.add("新时代大厦");
            dataList.add("新时代大厦");
            dataList.add("新时代大厦");
            dataList.add("新时代大厦");
            dataList.add("新时代大厦");
            dataList.add("新时代大厦");
            dataList.add("自建");
            for(int k = 0; k< monthList.size(); k++){
                dataList.add("100");
                dataList.add("100");
                dataList.add("100");
            }
            dataList.add("1000");
            dataList.add("1001");
            dataList.add("1002");
            dataList.add("测试备注");
            list.add(dataList);
        }
        return list;
    }

    private static List<List<String>> createTableHead(List<String> monthList) {
        List<List<String>> list = ListUtils.newArrayList();
        List<String> head0 = ListUtils.newArrayList();
        head0.add("合同编号");
        List<String> head1 = ListUtils.newArrayList();
        head1.add("项目名称");
        List<String> head2 = ListUtils.newArrayList();
        head2.add("楼宇名称");
        List<String> head3 = ListUtils.newArrayList();
        head3.add("房号");
        List<String> head4 = ListUtils.newArrayList();
        head4.add("租赁面积");
        List<String> head5 = ListUtils.newArrayList();
        head5.add("租客");
        List<String> head6 = ListUtils.newArrayList();
        head6.add("签约日期");
        List<String> head7 = ListUtils.newArrayList();
        head7.add("费用类型");
        List<String> head8 = ListUtils.newArrayList();
        head8.add("保证金");
        List<String> head9 = ListUtils.newArrayList();
        head9.add("条款");
        List<String> head10 = ListUtils.newArrayList();
        head10.add("免租标准");
        List<String> head11 = ListUtils.newArrayList();
        head11.add("付款类型");
        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        list.add(head11);
        for (String month : monthList) {
            Map<String,List<String>> monthMap = createTableMonthHeader(month);
            monthMap.forEach((k,v)->{
                List<String> monthHeadList = v;
                for (String s : monthHeadList) {
                    List<String> incomeMonthAmountExportDataHead = ListUtils.newArrayList();
                    incomeMonthAmountExportDataHead.add(k);
                    incomeMonthAmountExportDataHead.add(s);
                    list.add(incomeMonthAmountExportDataHead);
                }
            });
        }

        List<String> head12 = ListUtils.newArrayList();
        head12.add("合计应收");
        List<String> head14 = ListUtils.newArrayList();
        head14.add("合计实收");
        List<String> head15 = ListUtils.newArrayList();
        head15.add("合计欠收");
        List<String> head16 = ListUtils.newArrayList();
        head16.add("备注");
        list.add(head12);
        list.add(head14);
        list.add(head15);
        list.add(head16);
        return list;
    }

    private static Map<String,List<String>> createTableMonthHeader(String month){
        Map<String,List<String>> tableMap = new HashMap<>();
        List<String> monthList =  new ArrayList<>();
        monthList.add("应收");
        monthList.add("实收");
        monthList.add("欠收");
        tableMap.put(month, monthList);
        return tableMap;
    }

}



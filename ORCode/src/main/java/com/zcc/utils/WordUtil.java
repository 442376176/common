//package com.zcc.utils;
//
//import java.awt.*;
//
///**
// * @author zcc
// * @version 1.0
// * @date 2021/12/15 9:07
// */
//public class WordUtil {
//
//
//    public class Main {
//
//        public static void main(String[] args) {
//            //加载测试文档
//            Document document = new Document();
//            document.loadFromFile("sample.docx");
//
//            //插入文本水印
//            InsertTextWatermark(document.getSections().get(0));
//
//            //保存文档
//            document.saveToFile("textwatermark.docx",FileFormat.Docx );
//        }
//        //自定义方法指定文本水印字样，并设置成水印
//        private static void InsertTextWatermark(Section section){
//            TextWatermark txtWatermark = new TextWatermark();
//            txtWatermark.setText("内部使用");
//            txtWatermark.setFontSize(40);
//            txtWatermark.setColor(Color.red);
//            txtWatermark.setLayout(WatermarkLayout.Diagonal);
//            section.getDocument().setWatermark(txtWatermark);
//        }
//    }
//}

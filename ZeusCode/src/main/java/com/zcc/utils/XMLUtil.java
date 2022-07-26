package com.zcc.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class XMLUtil {
//    public static String getChartType(String name,String path,int index) {
//        try {
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            Document document;
//            document = documentBuilder.parse(new File(path));
//            short nodeType = document.getNodeType();
//            String nodeValue = document.getNodeValue();
//            NodeList nodeList = document.getElementsByTagName(name);
//            Node classNode = nodeList.item(index).getFirstChild();
//            String chartType = classNode.getNodeValue();
//            return chartType;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    public static void main(String[] args)throws Exception {

        //创建dom4j解析器对象

        SAXReader saxReader = new SAXReader();

        //通过字节流加载硬盘中的xml文件到内存

        Document xmlDocument = saxReader.read(new FileInputStream(new File("src\\main\\resources\\xml\\config.xml")));

        //获取根节点

        Element rootElement = xmlDocument.getRootElement();

        //获取根节点写所有book字节点形成的集合

        List<Element> list = rootElement.elements("book");

        //迭代

        for(Element element : list) {

            //获取book节点的id属性值

            String id = element.attributeValue("id");

            //分别获取book节点的title/author/price子节点的内容

            String title = element.element("title").getText().trim();

            String author = element.element("author").getText().trim();

            String price = element.element("price").getText().trim();



            //将来我们可以将上述属性值封装到JavaBean对象中的所有属性中，

            //加入到List<JavaBean>集合中

            System.out.println(id+":"+title+" "+author+" "+price);

            System.out.println("-------");

        }

    }


}

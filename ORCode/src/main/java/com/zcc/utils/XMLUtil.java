package com.zcc.utils;

import com.zcc.entity.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/11/19 9:26
 */
public class XMLUtil {
    // 读取xml配置文件
    public static String getMsgByTable(String name, String path, int index) throws Exception {
        // 创建文档对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File(path));

        // 获取文本节点
        NodeList nodeList1 = document.getChildNodes();
        NodeList nodeList = document.getElementsByTagName(name);
        Node item = nodeList.item(index);
        String value = item.getNodeValue().trim();
        return value;
    }

    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将String类型的xml转换成对象
     */
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }


    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\86151\\Desktop\\demo\\ORCode\\src\\main\\resources\\xml\\config.xml");
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(convertToXml(Book.builder().id(1).author("zcc").bookcaseid(1).name("测试").pages(100).price(999.99F).publish("测试").orderid(1).build()).getBytes());
        stream.flush();
        stream.close();
        System.out.println(getMsgByTable("book", "C:\\Users\\86151\\Desktop\\demo\\ORCode\\src\\main\\resources\\xml\\config.xml", 0));
    }
}

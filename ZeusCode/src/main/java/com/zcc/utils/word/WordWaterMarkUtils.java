package com.zcc.utils.word;

import com.microsoft.schemas.vml.CTShape;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import javax.xml.namespace.QName;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

/**
 * @ProjectName: building-lease
 * @Package: com.eazytec.utils
 * @ClassName: WordWaterMarkUtils
 * @Author: yangjianfei
 * @Description:
 * @Date: 2021/12/7 16:32
 * @Version: 1.0
 */
@Slf4j
public class WordWaterMarkUtils {
    private static String filePath = "D:\\building-lease\\file\\测试水印.docx";
    private static String imagePath = "D:\\building-lease\\file\\123.jpg";

    public static void main(String[] args) throws Exception{
        String waterMarkValue = "传入水印";
//        addWaterMark(filePath,waterMarkValue);
    }

    /**
     * 添加水印生成文件
     * @param in 要添加的水印word文件
     * @param waterMarkValue 水印字符串
     * @param height 字体高度
     * @throws IOException
     * @throws XmlException
     */
    public static void addWaterMark(InputStream in,String waterMarkValue,double height) throws IOException, XmlException {
        OutputStream out = null;
        try{
            XWPFDocument document = new XWPFDocument(in);
            XWPFParagraph paragraph = document.createParagraph();

            XWPFHeaderFooterPolicy xFooter = new XWPFHeaderFooterPolicy(document);
            xFooter.createWatermark(waterMarkValue);

            XWPFHeader header = xFooter.getHeader(XWPFHeaderFooterPolicy.DEFAULT);
            paragraph = header.getParagraphArray(0);
            XmlObject[] xmlobjects = paragraph.getCTP().getRArray(0).getPictArray(0).selectChildren(
                    new QName("urn:schemas-microsoft-com:vml", "shape"));

            if (xmlobjects.length > 0) {
                CTShape ctshape = (CTShape)xmlobjects[0];
                ctshape.setFillcolor("#d8d8d8");
                ctshape.setStyle(getWaterMarkStyle(ctshape.getStyle(),height) + ";rotation:315");
            }
            String afterPath = "D:\\building-lease\\file\\生成水印.docx";
            out = new FileOutputStream(afterPath);
            document.write(out);
            out.flush();
        }catch (IOException e){
            log.error("生成水印异常",e);
        }finally {
            if(out != null){
                out.close();
            }
            if(in != null){
                in.close();
            }
        }
    }

    /**
     * 修改水印样式（水印的字体大小）
     * @param styleStr  水印的原样式
     * @param height    水印文字高度
     * @return
     */
    public static String getWaterMarkStyle(String styleStr,double height){
        Pattern p= Pattern.compile(";");
        String[] strs = p.split(styleStr);
        for(String str : strs){
            if(str.startsWith("height:")){
                String heightStr = "height:" + height + "pt";
                styleStr = styleStr.replace(str,heightStr);
                break;
            }
        }
        return styleStr;
    }

}

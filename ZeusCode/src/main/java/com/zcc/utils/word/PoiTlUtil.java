package com.zcc.utils.word;

import com.deepoove.poi.XWPFTemplate;

import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.utils.word
 * @author: zcc
 * @date: 2022/5/27 11:10
 * @version:
 * @Describe:
 */
public class PoiTlUtil {


    public static void main(String[] args) throws Exception{
        XWPFTemplate template = XWPFTemplate.compile("C:\\Users\\86151\\Desktop\\zcc\\demo\\ORCode\\src\\main\\java\\com\\zcc\\utils\\word\\template.docx").render(new HashMap<String, Object>(){{
            put("title", "poi-tl Word模板引擎");
        }});
        FileOutputStream out = new FileOutputStream("output.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }
}

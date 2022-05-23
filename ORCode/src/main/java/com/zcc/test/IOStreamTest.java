package com.zcc.test;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/1/10 16:01
 */
public class IOStreamTest {

    String nullFile = "C:\\Users\\86151\\Desktop\\测试.docx";
    String path = "C:\\Users\\86151\\Desktop\\新建 文本文档.txt";

    @SneakyThrows
    @Test
    public void a(){
        File s = new File(nullFile);
        String name = s.getName();
        long usableSpace = s.length();
        boolean exists = s.exists();
        System.out.println(name);
        s.renameTo(s);
//        OutputStream outputStream = RestTemplateUtil.getInputStream("https://building-lease-test.app.zqtong.com", "/md/efile/FILE-2704091a2337482aa386498324947621?type=download", null, null, OutputStream.class);
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(s), "<changeme>"));
    }


}

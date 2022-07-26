package com.zcc.utils.file;

import org.junit.Test;

import java.io.*;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.utils.file
 * @author: zcc
 * @date: 2022/5/27 15:05
 * @version:
 * @Describe:
 */
public class ReadUtil {


    public static byte[] read(InputStream is) {

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            long from = 4000020001l;//最后一位是小写的L
            long to = 400003000l;//最后一位是小写的L
            long lines = 0l;
            int readChars = 0;
            byte[] buffer = new byte[256];
            while ((readChars = is.read(buffer, 0, 256)) != -1) {
                for (int i = 0; i < readChars; i++) {
                    if (buffer[i] == '\n') {
                        if (lines == from - 2) {
                            for (int j = i + 1; j < readChars; j++) {
                                if (buffer[j] == '\n') {
                                    ++lines;
                                }
                            }
                            i = buffer.length - 1;
                        }
                        if (lines > from - 2 && lines < to) {
                            for (int j = i + 1; j < readChars; j++) {
                                if (buffer[j] == '\n') {
                                    ++lines;
                                }
                            }
                            i = buffer.length - 1;
                        }
                        if (lines == to) {
                            for (int j = i + 1; j < readChars; j++) {
                                if (buffer[j] == '\n') {
                                    ++lines;
                                }
                            }
                            i = buffer.length - 1;
                        }
                        ++lines;
                    }
                    output.write(buffer[i]);
                }
                if (lines > to) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
        }
        return output.toByteArray();
    }



    @Test
    public void test() throws Exception {
        try {

            InputStream in  =  ClassLoader.getSystemResourceAsStream("src/main/java/com/zcc/out/1.jpg");
            InputStream resourceAsStream = ReadUtil.class.getResourceAsStream("resource/1.jpg");
            System.out.println(resourceAsStream);
        } catch (Exception e) {

        }
        InputStream is = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\86151\\Desktop\\git回退.txt")));
        byte[] read = read(is);
        System.out.println(read.length / 1024);
    }

    public static void main(String[] args) {

        InputStream is = null;
        try {
            long from = 4000020001l;//最后一位是小写的L
            long to = 400003000l;//最后一位是小写的L
            long startTime = System.currentTimeMillis();
            long lines = 0l;
            int readChars = 0;
            byte[] buffer = new byte[256];
            is = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\86151\\Desktop\\testSplitFile.zip")));
            while ((readChars = is.read(buffer, 0, 256)) != -1) {
                for (int i = 0; i < readChars; i++) {
                    if (buffer[i] == '\n') {
                        if (lines == from - 2) {
                            for (int j = i + 1; j < readChars; j++) {
                                if (buffer[j] == '\n') {
                                    ++lines;
                                }
                            }
                            System.out.print(new String(buffer, i + 1, buffer.length - i - 1));
                            i = buffer.length - 1;
                        }
                        if (lines > from - 2 && lines < to) {
                            for (int j = i + 1; j < readChars; j++) {
                                if (buffer[j] == '\n') {
                                    ++lines;
                                }
                            }
                            System.out.print(new String(buffer, 0, 256));
                            i = buffer.length - 1;
                        }
                        if (lines == to) {
                            for (int j = i + 1; j < readChars; j++) {
                                if (buffer[j] == '\n') {
                                    ++lines;
                                }
                            }
                            System.out.print(new String(buffer, 0, i));
                            i = buffer.length - 1;
                        }
                        ++lines;
                    }
                }
                if (lines > to) {
                    break;
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("\n方案八读取总行数 : " + lines + " 共耗时 : " + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {

            }
        }
    }
}

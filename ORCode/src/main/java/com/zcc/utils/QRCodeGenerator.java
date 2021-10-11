package com.zcc.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
/**
 * @author zcc
 * @date 2021/07/05
 */

public class QRCodeGenerator {



    static class QrCodeTest {

        public static void main(String[] args) throws Exception {
            // 存放在二维码中的内容
            String text = "https://www.baidu.com/";
            // 嵌入二维码的图片路径
            String imgPath = "src/main/resources/1.jpg";
            // 生成的二维码的路径及名称
            String destPath = "src/main/resources/jam.jpg";
            //生成二维码
            QRCodeUtil.encode(text, imgPath, destPath, true);
            // 解析二维码
            String str = QRCodeUtil.decode(destPath);
            // 打印出解析出的内容
            System.out.println(str);

        }

    }


    private static final String QR_CODE_IMAGE_PATH = "src/main/resources/ORCodeImage.jpg";

    private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    public static void main(String[] args) {
        try {
            generateQRCodeImage("This is my first QR Code", 350, 350, QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }

    }

}

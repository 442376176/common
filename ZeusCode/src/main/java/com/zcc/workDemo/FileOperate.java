package com.zcc.workDemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @ProjectName: ZeusCode
 * @ClassName: com.zcc
 * @author: zcc
 * @date: 2022/10/19 9:41
 * @version:
 * @Describe:
 */
public class FileOperate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        File file = new File(scanner.nextLine());
        if (!file.exists()) {
            System.out.println("目录不存在");
            return;
        }
        List<File> allFileList = new ArrayList<>();
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File dir = files[i];
            if (dir.isDirectory()) {
                File name = new File(dir.getParent() + File.separator + i);
                if (dir.renameTo(name)) {
                    continue;
                }
                break;
            }
        }
    }

}

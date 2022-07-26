package com.zcc.algorithm.huawei;

import com.zcc.utils.JudgeNullUtil;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.algorithm.huawei
 * @author: zcc
 * @date: 2022/6/27 10:24
 * @version:
 * @Describe:
 */
public class Test2 {
    public static int lastWordLength(String str){
        int index =str.lastIndexOf(" ");
        return str.length()-1-index;
    }
    @Test
    public void test(){
//        System.out.println(lastWordLength("hello nowcoder"));
//        String s = "https://building-lease-test.app.zqtong.com/api/systemSetting/v1/template/download?fileId=FILE-8e1f51dc08d346029e4b26c383d08934";
//        String substring = s.substring(s.indexOf("=") + 1, s.length());
//        System.out.println(substring);

        Map<Object, Object> a = new HashMap<>();
        a.put("str","123,456,789");
        Map<Object, Object> b = new HashMap<>();
        List<Map> c = new ArrayList<>();
        c.add(b);
        c.add(a);
        List<Long> str = c.stream()
                .flatMap(item -> {
                    String fileIds = (String) item.get("str");
                    if (JudgeNullUtil.isNotNull(fileIds)) {
                        return Arrays.stream(fileIds.split(","));
                    }
                    return null;
                })
                .map(Long::parseLong)
                .collect(Collectors.toList());
        System.out.println(str);

    }
}


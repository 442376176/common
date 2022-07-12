package com.zcc.designPattern.adapter;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.designPattern.adapter
 * @author: zcc
 * @date: 2022/6/24 13:21
 * @version:
 * @Describe:
 */
public interface Controller {
    class HttpController implements Controller{
        public void doHttpHandler(){
            System.out.println("http...");
        }
    }
}

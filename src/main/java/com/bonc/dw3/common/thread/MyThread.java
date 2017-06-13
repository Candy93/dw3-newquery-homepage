package com.bonc.dw3.common.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Candy on 2017/6/13.
 */
@Component
public class MyThread extends Thread {

    private static Logger log = LoggerFactory.getLogger(MyThread.class);

    @Autowired
    RestTemplate restTemplate; //发送请求的rest对象

    String url; //请求的url地址
    String paramStr; //请求的参数
    public Object result = null; //请求返回的结果
    long time; //从请求到返回耗时

    public MyThread(String url, String paramStr){
        this.url = url;
        this.paramStr = paramStr;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        this.result = restTemplate.postForObject(url, paramStr, Object.class);
        this.time = System.currentTimeMillis() - start;
        log.info(paramStr + "服务返回结果耗时：" + this.time + "ms");
    }
}

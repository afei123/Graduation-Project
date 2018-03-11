package com.example.demo.utils;

import org.springframework.util.StringUtils;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by AFei on 2017/9/29.
 */
public class DateUtils {
    private static final String YMDHMS_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String YMD_FORMAT = "yyyy-MM-dd";


    public static String getWebDatetime(String format) {
        try {
            // 判断当前是否传入URL
            //if (!StringUtils.isEmpty(webuUrl)) {
                URL url = new URL("http://www.baidu.com");// 获取url对象
                URLConnection uc = url.openConnection();// 获取生成连接对象
                uc.connect();// 发出连接请求
                long ld = uc.getDate();// 读取网站日期时间
                Date date = new Date(ld);// 转化为时间对象
                SimpleDateFormat sdf = new SimpleDateFormat(
                        format != null ? format : YMDHMS_FORMAT, Locale.CHINA);// 输出北京时间
                return sdf.format(date);
            /*} else {
                System.out.println("URL Error!!!");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

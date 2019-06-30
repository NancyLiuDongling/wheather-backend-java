package com.weather.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.weather.service.WeatherDataService;
import com.weather.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassNmae WeatherDataService
 * @Description TODO
 * @Author fangdz
 * @Date 2019/6/29 21:03
 * @Version 1.0
 */
@Service
public class WeatherDataServiceImpl  implements WeatherDataService {
    public static final String APPKEY = "203721924 ";
    public static final String APPSECRET = "7xa18w2ebtei60qhk30est0jluxmc8nq";
    public static final String APPCODE = "ea0362dfd42e46cca5615944cb5c6365 ";

    public String weatherData(String city){

        String host = "https://jisutqybmf.market.alicloudapi.com";
        String path = "/weather/query";
        String method = "GET";
        String appcode = APPCODE;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("cityid", city);

        //JSONObject jsonObject = null;
        String result = null;
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");

// 设置服务器允许浏览器发送请求都携带cookie
            response.setHeader("Access-Control-Allow-Credentials","true");
// 允许的访问方法
            response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
// Access-Control-Max-Age 用于 CORS 相关配置的缓存
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept,mid,X-Token");

            System.out.println(response.toString());
             result = EntityUtils.toString(response.getEntity());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            //sonObject = (JSONObject)JSONObject.parse(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}

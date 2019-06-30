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

    public JSONObject weatherData(String city, String citycode, String cityid, String ip, String location){

        String host = "https://jisutqybmf.market.alicloudapi.com";
        String path = "/weather/query";
        String method = "GET";
        String appcode = APPCODE;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("city", city);
        querys.put("citycode", citycode);
        querys.put("cityid", cityid);
        querys.put("ip", ip);
        querys.put("location", location);
        JSONObject jsonObject = null;

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
            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            jsonObject = (JSONObject)JSONObject.parse(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;

    }
}

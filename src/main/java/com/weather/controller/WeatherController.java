package com.weather.controller;

import com.alibaba.fastjson.JSONObject;
import com.weather.entity.ParamBody;
import com.weather.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassNmae WeatherController
 * @Description TODO
 * @Author fangdz
 * @Date 2019/6/29 21:02
 * @Version 1.0
 */

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherDataService weatherDataService;

    @PostMapping("/data")
    @ResponseBody
    public Object init(@RequestBody ParamBody paramBody){
        String city = paramBody.getCity();
        String citycode = paramBody.getCitycode();
        String cityid = paramBody.getCityid();
        String ip = paramBody.getIp();
        String location = paramBody.getLocation();
        JSONObject jsonObject =  weatherDataService.weatherData(city,citycode,cityid,ip,location);
        return jsonObject;
    }
}

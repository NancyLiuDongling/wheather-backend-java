package com.weather.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface WeatherDataService {

    String weatherData(String city);

}
/*public interface WeatherDataService {

    JSONObject weatherData(String city, String citycode, String cityid, String ip, String location);

}*/

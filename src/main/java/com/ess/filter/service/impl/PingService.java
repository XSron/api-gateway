package com.ess.filter.service.impl;

import com.ess.filter.config.ConfigValue;
import com.ess.filter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PingService {

    private RestTemplate restTemplate;
    private ConfigValue configValue;

    @Autowired
    public PingService(RestTemplate restTemplate, ConfigValue configValue) {
        this.restTemplate = restTemplate;
        this.configValue = configValue;
    }

    public HashMap<String, Object> ping(){
        HashMap<String, Object> objs = new HashMap<>();
        objs.put("user", user());
        objs.put("product", product());
        objs.put("report", report());
        objs.put("upload", upload());
        objs.put("payment", payment());
        objs.put("shopping", shopping());
        return objs;
    }

    private Object user() {
        return restTemplate.getForObject(configValue.getUserUrl()+"/api/users/username/user", Object.class);
    }

    private Object product() {
        try {
            return restTemplate.getForObject(configValue.getProductUrl(), Object.class);
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    private Object report() {
        try {
            return restTemplate.getForObject(configValue.getReportUrl(), Object.class);
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    private Object upload() {
        try {
            return restTemplate.getForObject(configValue.getUploadUrl(), String.class);
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    private Object payment() {
        try {
            return restTemplate.getForObject(configValue.getPaymentUrl(), Object.class);
        }catch (Exception ex){
            return ex.getMessage();
        }

    }

    private Object shopping() {
        try {
            return restTemplate.getForObject(configValue.getShoppingUrl(), Object.class);
        }catch (Exception ex){
            return ex.getMessage();
        }

    }
}

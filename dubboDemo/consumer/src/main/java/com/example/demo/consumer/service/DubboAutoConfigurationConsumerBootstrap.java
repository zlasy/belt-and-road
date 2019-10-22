package com.example.demo.consumer.service;

import com.dangdang.ddcloud.httpclient.DdcloudHttpClient;
import com.dangdang.ddcloud.httpclient.DdcloudResponse;
import com.dangdang.kefu.msg.center.facade.MessageService;
import com.dangdang.kefu.msg.center.facade.param.message.MaxSeqParams;
import com.dangdang.kefu.outer.api.facade.CustService;
import com.dangdang.kefu.outer.api.facade.response.ApiResponse;
import com.dangdang.kefu.outer.api.facade.response.CustInfoRespData;
import com.example.demo.consumer.config.DynamicProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class DubboAutoConfigurationConsumerBootstrap {

//    @Reference(version = "1.0.0", url = "dubbo://10.255.242.223:8099")
    @Reference(registry = "r1")
    private CustService custService;
    @Reference(registry = "r2")
    private MessageService messageService;
    @Autowired
    private DynamicProperties dynamicProperties;
    @Autowired
    private DdcloudHttpClient client;

    @RequestMapping(path="/cust/{id}", method = RequestMethod.GET)
    public ApiResponse<CustInfoRespData> getCustInfo(@PathVariable Integer id) {
        return custService.getCustInfo(id, 1);
    }

    @RequestMapping("/dynamic")
    public String config1() {
        return dynamicProperties.toString();
    }

    @RequestMapping(path="/test/{id}", method = RequestMethod.GET)
    public Map<String, Object> test(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("cust", custService.getCustInfo(id, 1));
            MaxSeqParams param = new MaxSeqParams();
            param.setTenantId(1);
            param.setRoomId(2234L);
            result.put("company", messageService.getMaxSeq(param));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/http")
    public String http() {
        try {
            DdcloudResponse response = client.get("http://10.255.209.70:9100/login.html")
                    .connectTimeout(2000)//可以不设置，默认值为2000ms
                    .socketTimeout(5000)//可以不设置，默认值为2000ms
                    .addHeader("test_header", "get_header")
                    .retry(2)//仅针对socketTimeout异常会进行重试，如果不需要重试，则不设置该配置
                    .execute();
            return response.asString();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
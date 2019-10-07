package com.example.demo.consumer;

import com.dangdang.kefu.msg.center.facade.MessageService;
import com.dangdang.kefu.msg.center.facade.param.message.MaxSeqParams;
import com.dangdang.kefu.outer.api.facade.CustService;
import com.dangdang.kefu.outer.api.facade.response.ApiResponse;
import com.dangdang.kefu.outer.api.facade.response.CustInfoRespData;
import com.example.demo.config.DynamicProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
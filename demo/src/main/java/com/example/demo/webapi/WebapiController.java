package com.example.demo.webapi;

import com.dangdang.ddcloud.httpclient.DdcloudHttpClient;
import com.dangdang.ddcloud.httpclient.DdcloudResponse;
import com.dangdang.ddcloud.webapi.ApiController;
import com.dangdang.ddcloud.webapi.filter.ApiFilter;
import com.dangdang.kefu.outer.api.facade.CustService;
import com.dangdang.kefu.outer.api.facade.response.ApiResponse;
import com.dangdang.kefu.outer.api.facade.response.CustInfoRespData;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.net.URISyntaxException;

@ApiController
//@RestController
@Slf4j
public class WebapiController {

    @Reference(registry = "r1")
    private CustService custService;

    @Autowired
    private DdcloudHttpClient client;

    @GetMapping("cust2")
    @ApiFilter(LogFilter.class)
    public ApiResponse<CustInfoRespData> getCustInfo(@QueryParam("id") Integer id) {
        return custService.getCustInfo(id, 1);
    }


    @GetMapping("http")
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
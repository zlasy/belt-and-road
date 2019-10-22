package com.example.demo.provider.service;

import com.example.demo.api.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("rest")
@Service(timeout = 3000)
@Component
@Slf4j
public class DemoServiceImpl implements DemoService {

    @GET
    @Path("/{str}")
    @Override
    public String test(@PathParam("str") String str) {
        log.info(str);
        return str;
    }
}

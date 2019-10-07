package com.example.demo.provider.service;

import com.example.demo.provider.facade.TestFacade;
import org.apache.dubbo.config.annotation.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("rest")
@Service
public class TestService implements TestFacade {

    @GET
    @Path("/{var}")
    @Override
    public String echo(@PathParam("var") String var) {
        return var;
    }
}

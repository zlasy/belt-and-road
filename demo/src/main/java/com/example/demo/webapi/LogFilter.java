package com.example.demo.webapi;

import com.dangdang.ddcloud.webapi.filter.AbstractApiFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LogFilter extends AbstractApiFilter {

    @Override
    protected String getDescription() {
        return "打印日志";
    }

    @Override
    protected Object beforeReturn(HttpServletRequest request, HttpServletResponse response, Method method, Object object) {
        System.out.println(request.getParameterMap() + "|" + object.toString());
        return object;
    }
}

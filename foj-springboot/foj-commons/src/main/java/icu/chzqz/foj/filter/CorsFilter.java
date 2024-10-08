package icu.chzqz.foj.filter;


import icu.chzqz.foj.entity.exception.AccessDeniedException;
import icu.chzqz.foj.handler.GlobalExceptionHandler;
import icu.chzqz.foj.properties.MessageProperty;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
public class CorsFilter implements Filter {


    @Autowired
    GlobalExceptionHandler globalExceptionHandler;
    @Autowired
    MessageProperty messageProperty;
    @Autowired
    HandlerExceptionResolver handlerExceptionResolver;

    //销毁时候调用的方法
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {
            log.info("跨域处理");
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) res;

            response.setHeader("Access-Control-Allow-Origin", " http://localhost:8081");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "content-type");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            chain.doFilter(req, res);
        }catch (Exception e){
            handlerExceptionResolver.resolveException((HttpServletRequest) req, (HttpServletResponse) res,null,new AccessDeniedException(messageProperty.notLogin));
        }
    }

    //初始化调用的方法
    //当服务器 被启动的时候，调用
    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
package icu.chzqz.foj.interceptor;

import icu.chzqz.foj.exception.AccessDeniedException;
import icu.chzqz.foj.properties.MessageProperty;
import icu.chzqz.foj.util.BaseContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class PermissionsInterceptor implements HandlerInterceptor {

    @Autowired
    MessageProperty messageProperty;

    Map<String,Integer> map;

    public PermissionsInterceptor() {
        map = new HashMap<>();
        map.put("user",0);
        map.put("setter",1);
        map.put("admin",2);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL = request.getRequestURI();
        String[] split = requestURL.split("/");
        int authority = map.getOrDefault(split[1],-1);
        if(authority > (Integer) BaseContextUtil.getBaseContext().getOrDefault("authority",-2)){
            throw new AccessDeniedException(messageProperty.permissionDenied);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

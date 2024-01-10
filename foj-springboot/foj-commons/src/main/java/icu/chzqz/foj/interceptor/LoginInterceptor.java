package icu.chzqz.foj.interceptor;

import icu.chzqz.foj.entity.exception.AccessDeniedException;
import icu.chzqz.foj.properties.MessageProperty;
import icu.chzqz.foj.util.BaseContextUtil;
import icu.chzqz.foj.util.JWTUtil;
import jakarta.servlet.http.Cookie;
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
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    MessageProperty messageProperty;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){

            log.info("登录验证" );
            for (Cookie cookie : cookies) {
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    try {
                        Map claims = JWTUtil.parseToken(token);
                        log.info("jwt解析: {}",claims);
                        BaseContextUtil.setBaseContext(claims);
                        return true;
                    }
                    catch (Exception e){
                        throw new AccessDeniedException(messageProperty.loginExpired);
                    }
                }
            }
        }
        throw new AccessDeniedException(messageProperty.notLogin);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextUtil.setBaseContext(new HashMap());
    }
}

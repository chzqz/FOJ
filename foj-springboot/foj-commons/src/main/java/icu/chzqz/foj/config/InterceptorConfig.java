package icu.chzqz.foj.config;

import icu.chzqz.foj.interceptor.LoginInterceptor;
import icu.chzqz.foj.interceptor.PermissionsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private PermissionsInterceptor permissionsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/regist")
                .excludePathPatterns("/user/questions/list")
                .excludePathPatterns("/user/question/*")
                .excludePathPatterns("/user/language/list")
                .excludePathPatterns("/user/tags/list");

        registry.addInterceptor(permissionsInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/regist")
                .excludePathPatterns("/user/questions/list")
                .excludePathPatterns("/user/question/*")
                .excludePathPatterns("/user/language/list")
                .excludePathPatterns("/user/tags/list");
    }

//    /**
//     * 跨域支持
//     * @param registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("localhost:8081")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .allowedHeaders("*")
//                .maxAge(3600 * 24);
//    }

}

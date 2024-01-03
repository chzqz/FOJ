package icu.chzqz.foj.util;

import java.util.Map;

/**
 * 基本上下文工具类, 存储这个请求的一些属性
 */
public class BaseContextUtil {
    static ThreadLocal<Map> threadLocal = new ThreadLocal<>();

    public static void setBaseContext(Map BaseContext){
        threadLocal.set(BaseContext);
    }

    public static Map getBaseContext(){
        return threadLocal.get();
    }
}

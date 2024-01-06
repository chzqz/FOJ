package icu.chzqz.foj.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 信息类
 */
@ConfigurationProperties(prefix = "foj.message")
@Component
@Data
public class MessageProperty {
    /**
     * 登录过期
     */
    public String loginExpired;

    /**
     * 用户未登录
     */
    public String notLogin;

    /**
     * 无权访问
     */
    public String permissionDenied;

    /**
     * 登录失败
     */
    public String loginFailed;

    /**
     *  提交失败
     */
    public String submitFailed;
    /**
     * 参数错误
     */
    public String argumentError;
    /**
     * 答案错误
     */
    public String wrongAnswer;
    /**
     * 运行时间超时
     */
    public String cpuTimeExceeded;
    /**
     * 内存超限
     */
    public String memoryExceeded;
    /**
     * 邮箱格式错误
     */
    public String emailFormatError;
    /**
     * 密码格式不合格
     */
    public String unqualifiedPassword;
    /**
     * 邮箱已注册
     */
    public String emailExists;
    /**
     * 用户名已存在
     */
    public String usernameExists;
    /**
     * 密码错误
     */
    public String passwordError;
    /**
     * 不支持的语言
     */
    public String unsupportedLanguage;
}

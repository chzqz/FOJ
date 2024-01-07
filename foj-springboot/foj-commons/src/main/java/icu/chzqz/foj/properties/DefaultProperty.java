package icu.chzqz.foj.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "foj.default")
public class DefaultProperty {
    public Long maxTime;
    public Long maxMemory;
    public Long maxStack;
    public Long procLimit;
    public Integer fileMax;
    public String avatarPath;
    public String testcasePath;
}

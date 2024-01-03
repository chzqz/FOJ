package icu.chzqz.foj.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class JudgeServerProperty {
    @Value("${foj.judge-server}")
    String judgeServer;
}

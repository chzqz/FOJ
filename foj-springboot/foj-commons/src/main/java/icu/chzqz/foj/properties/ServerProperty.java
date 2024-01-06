package icu.chzqz.foj.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ServerProperty {
    @Value("${foj.judge-server}")
    String judgeServer;

    @Value("${foj.resource-server}")
    String resourceServer;
}

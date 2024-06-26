package icu.chzqz.foj.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "foj")
@Data
public class LanguageProperty {
    List<String> language;
}

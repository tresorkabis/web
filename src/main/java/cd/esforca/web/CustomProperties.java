package cd.esforca.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "cd.esforca.web")
public class CustomProperties {

    private String apiUrl;
}

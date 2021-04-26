package com.chauyiu1994.livibankans;

import com.chauyiu1994.livibankans.config.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@ComponentScan(basePackages = {
        "com.chauyiu1994.livibankans"
})
@EnableAutoConfiguration
@PropertySources({
        @PropertySource(value = "classpath:liviBankAns-test.yaml", factory = YamlPropertySourceFactory.class)
})
public class IntegrationTestConfig {
}

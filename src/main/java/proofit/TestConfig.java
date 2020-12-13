package proofit;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootConfiguration
@ComponentScan
@PropertySource("classpath:application.yml")
//@EnableConfigurationProperties(Config.class)
public class TestConfig {
}

package com.agree.chattingapi.conf;

import ch.qos.logback.classic.Logger;
import com.agree.chattingapi.utils.UserInterceptor;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserInterceptor userInterceptor;
    private static String webIp = new ApplicationConfig().getWebIp();
    private static final Logger log = (Logger) LoggerFactory.getLogger(WebConfig.class);

    public WebConfig(UserInterceptor userInterceptor) {
        this.userInterceptor = userInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(webIp + ":3000", webIp + ":23000", "http://code-server:3000", "http://code-server:23000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
}

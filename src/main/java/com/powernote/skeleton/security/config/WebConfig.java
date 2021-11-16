package com.powernote.skeleton.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 자동 설정 :
 * implements WebMvcConfigurer  ( @EnableWebMvc 없이 ) 하면 WebMvcAutoConfiguration 활설화됨.
 * 리소스 설정. yml 파일내에서 설정.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedMethods("*")
//                .allowCredentials(true)
//                .maxAge(3000);
//    }

}

/*
 * 수동설정 :
 * @EnableWebMvc + implements WebMvcConfigurer  or
 * extends WebMvcConfigurationSupport  이용하면
 * WebMvcAutoConfiguration 비활성화 되어  수동설정 진행 하면 된다 .
 */
/*
@Configuration
public class WebConfig extends WebMvcConfigurationSupport  {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
*/

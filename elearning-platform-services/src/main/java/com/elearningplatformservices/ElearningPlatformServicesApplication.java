package com.elearningplatformservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
public class ElearningPlatformServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElearningPlatformServicesApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("E-Platform").select()
                .apis(RequestHandlerSelectors.basePackage("com.elearningplatformservices"))
                .paths(any()).build().apiInfo(new ApiInfo("Platform",
                        "Electronic Learning Platform", "1.0.0", null,
                        new Contact("Fortech", null, null), null, null));
    }
}

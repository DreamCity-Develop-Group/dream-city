package com.dream.city.base.config;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${server.servlet.context-path}")
    private String pathMapping;
    @Value("${server.port}")
    private String port;

    private ApiInfo initApiInfo(){
        ApiInfo apiInfo =  new ApiInfo("Dream City Platform APIs",
                initContextInfo(),
                "1.0.0",
                "平台服务条款",
                "DreamCity Tech Team",
                "版权：Dream City",
                "www.dream-city.com");
        return apiInfo;
    }

    private String initContextInfo(){
        StringBuffer sb = new StringBuffer();
        sb.append("REST API 设计在细节上有很多自己独特的需要注意的技巧，并且对开发人员在构架设计能力上比传统 API 有着更高的要求。")
                .append("<br/>")
                .append("本文通过翔实的叙述和一系列的范例，从整体结构，到局部细节，分析和解读了为了提高易用性和高效性，REST API 设计应该注意哪些问题以及如何解决这些问题。");

        return sb.toString();
    }

    @Bean
    public Docket resfulApi(){
        System.out.println("http://localhost:"+port + pathMapping + "/swagger-ui.html");
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("RestfulApi")
//                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .pathMapping(pathMapping) // base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(doFilteringRules())
                .build()
                .apiInfo(initApiInfo());
    }

    /**
     * 设置过滤规则
     * 这里的过滤规则支持正则匹配
     * @return
     */
    private Predicate<String> doFilteringRules() {
        return or(
                regex("/hello.*"),
                regex("/consumer.*")
        );
    }
}

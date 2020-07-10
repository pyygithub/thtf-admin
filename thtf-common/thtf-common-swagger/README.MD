# Swagger2 token配置说明

## 解决方案

- [Swagger2 添加HTTP head参数](http://blog.csdn.net/u014044812/article/details/71473226)

- [Swagger2 添加HTTP head参数，解决用户是token信息保留](http://www.cnblogs.com/fengli9998/p/7852685.html)

但上述方案存在2个不足之处：

1. 需要在每个接口下单独输入参数

2. 全局配置了参数，如果某些接口（如login等）不需要参数，则必须在改接口中通过annotation现实声明，较为麻烦

  

## 综上，选择优化方案如下：

- 通过Swagger2的securitySchemes配置全局参数：如下列代码所示，securitySchemes的ApiKey中增加一个名为“Authorization”，type为“header”的参数。


```java
    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }
```

- 在Swagger2的securityContexts中通过正则表达式，设置需要使用参数的接口（或者说，是去除掉不需要使用参数的接口），如下列代码所示，通过PathSelectors.regex("^(?!auth).*$")，所有包含"auth"的接口不需要使用securitySchemes。即不需要使用上文中设置的名为“Authorization”，type为“header”的参数。

```java
	private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                // 去除掉不需要使用参数的接口,
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }
```

设置完成后进入SwaggerUI，右上角出现“Authorization”按钮，点击即可输入我们配置的参数。
对于不需要输入参数的接口（上文所述的包含auth的接口），在未输入Authorization参数就可以访问。
其他接口则将返回401错误。点击右上角“Authorization”按钮，输入配置的参数后即可访问。参数输入后全局有效，无需每个接口单独输入。

![](https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3108933280,3670081988&fm=15&gp=0.jpg)

至此，完成Swagger2 非全局、无需重复输入的Head参数配置。
Swagger2的相关完整代码如下（工程基于Springboot）：

```java
package com.thtf.common.swagger.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------
 * Swagger配置类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-13 14:30:05
 * 版本：  v1.0
 * ---------------------------
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("使用Swagger2构建RESTful APIs")
                .description("Swagger api接口文档")
                .termsOfServiceUrl("")
                .contact(new Contact("thtf-admin", "http://springcloud.cn", ""))
                .license("Apache License Version 2.0")
                .version("2.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                // 去除掉不需要使用参数的接口,
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}

```
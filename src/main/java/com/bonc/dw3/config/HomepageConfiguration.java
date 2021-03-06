package com.bonc.dw3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Candy
 */
@Configuration
@EnableSwagger2
public class HomepageConfiguration {
	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInf()).select()
                // 要扫描的API(Controller)基础包
				.apis(RequestHandlerSelectors.basePackage("com.bonc.dw3.controller"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo buildApiInf() {
		return new ApiInfoBuilder().title("DW3.0-新版查询项目中使用Swagger2 UI构建API文档").contact("首页查询").version("1.0").build();
	}
}

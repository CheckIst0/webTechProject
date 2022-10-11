//package web.tech.project;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class ApplicationConfiguration {
//
//    @Bean
//    public Docket menuApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("web.tech.project.api.controllers")).build()
//                .apiInfo(apiInfo())
//                .useDefaultResponseMessages(false);
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("MenuApp")
//                .description("Сервис MenuApp")
//                .license("License")
//                .licenseUrl("http://unlicense.org")
//                .termsOfServiceUrl("")
//                .version(getClass().getPackage().getImplementationVersion())
//                .build();
//    }
//}

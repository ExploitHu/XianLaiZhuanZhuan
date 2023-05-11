package biao.community.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@CrossOrigin
@Configuration
@RestController
public class Download  extends WebMvcConfigurationSupport{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //下载文件夹
        registry.addResourceHandler("/download/**").
                addResourceLocations("file:/root/download/");
        //图片文件夹
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:/root/images/");
        //门户网站文件夹
        registry.addResourceHandler("/home/**")
                .addResourceLocations("file:/root/home/");

        //  js css 所在文件static映射，方便页面引用js css文件
        //registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

}
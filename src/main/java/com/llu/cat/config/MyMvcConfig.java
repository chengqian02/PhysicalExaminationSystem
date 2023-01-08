package com.llu.cat.config;


import com.llu.cat.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @create 2022-02-23 14:14
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//        registry.addViewController("/atguigu").setViewName("success");
        registry.addViewController("/").setViewName("/login");
        registry.addViewController("/login.html").setViewName("/login");
        registry.addViewController("/main_admin.html").setViewName("/admin/index_admin");
        registry.addViewController("/main_user.html").setViewName("/user/index_user");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html","/","/user_login","/css/**","/js/**","/font/**","/picture/**","/webjars/**","/user_register","/public/","/META-INF/resources/","/forgetPassword");
    }

    //    @Bean
//    public  WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
//        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("");
//            }
//        };
//        return adapter;
//    }


}

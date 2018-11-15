package com.shinemo.mq-server.web.configuration;

import com.shinemo.mq-server.web.interceptor.DemoInterceptor;
import com.shinemo.mq-server.web.filter.DemoFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

/**
 * Created by zhangyan on 15/11/2018.
 */
@Configuration
@MapperScan(basePackages = {"com.shinemo.mq-server.dal.user.mapper"})
@ImportResource(locations = {"classpath*:mq-server-core.xml"})
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // AntPathMatcher.match(String pattern, String path)
        // 当要匹配所有链接时，urlPattern="/**"是最好的
        registry.addInterceptor(demoInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 添加过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean registerDemoFilter() {
        // org.apache.catalina.core.ApplicationFilterFactory.createFilterChain(ServletRequest request, Wrapper wrapper, Servlet servlet)
        // matchDispatcher(FilterMap filterMap, DispatcherType type)
        // matchFiltersURL(FilterMap filterMap, String requestPath) -> matchFiltersURL(String testPath, String requestPath)
        // 当要匹配所有链接时，urlPattern="*"是最好的
        FilterRegistrationBean registration = new FilterRegistrationBean(demoFilter());
        registration.addUrlPatterns("*");
        registration.addInitParameter("applicationName", "mq-server");
        registration.setName("demoFilter");
        return registration;
    }

    public Filter demoFilter() {
        return new DemoFilter();
    }

    public HandlerInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }
}

package cloud.liupeng.api.interceptor;

import com.alibaba.cloud.seata.web.SeataHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截所有请求,过滤静态页面请求
 *
 * @author liupeng
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public GlobalInterceptor getGlobalInterceptor() {
        return new GlobalInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getGlobalInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/lkadoc.html");
        // 确保 seata xid 可以正常传递，参考 SpringCloud xid无法传递？http://seata.io/zh-cn/docs/overview/faq.html
        registry.addInterceptor(new SeataHandlerInterceptor()).addPathPatterns("/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

package cloud.liupeng.exception;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 网关异常处理配置类
 *
 * @author liupeng
 */
@ConfigurationProperties(prefix = WebFluxExceptionProperties.PREFIX)
public class WebFluxExceptionProperties {

    /**
     * 默认前缀
     */
    public static final String PREFIX = "spring-cloud-alibaba.gateway.exception";

    /**
     * 网关全局处理开关，默认：true
     */
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

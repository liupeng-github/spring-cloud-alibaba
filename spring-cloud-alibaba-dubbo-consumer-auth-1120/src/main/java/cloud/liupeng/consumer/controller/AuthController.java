package cloud.liupeng.consumer.controller;

import cloud.liupeng.domain.entity.auth.Users;
import cloud.liupeng.dubbo.auth.AuthApacheService;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据消费层，认证接口
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "AuthController", description = "数据中转层，认证接口，Dubbo 消费服务，端口号：1120")
@RestController
public class AuthController {

    @Value("${server.port}")
    private String port;

    @DubboReference
    private AuthApacheService authApacheService;

    /**
     * 登录认证
     *
     * @param username
     * @return
     */
    @Trace
    @Tag(key = "loginAuthDatalayer", value = "username")
    @LKAMethod(value = "loginAuthDatalayer 方法", description = "登录认证")
    @LKAParam(name = "username", value = "用户名")
    @PostMapping("/loginAuthDatalayer/{username}")
    public Users loginAuthDatalayer(@PathVariable("username") String username) {
        return authApacheService.loginAuthDatalayer(username);
    }
}

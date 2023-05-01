package cloud.liupeng.controller;

import cloud.liupeng.domain.entity.auth.Users;
import cloud.liupeng.api.utils.JSONResult;
import cloud.liupeng.api.sentinel.SentinelFallback;
import cloud.liupeng.openfeign.service.datalayer.DataLayerAuthService;
import cn.hutool.http.HttpStatus;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证服务
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "AuthController", description = "认证服务，端口号：2221")
@RestController
public class AuthController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private DataLayerAuthService dataLayerAuthService;

    @Trace
    @LKAMethod(value = "index 方法", description = "单体服务测试方法")
    @GetMapping("/index")
    @SentinelResource(value = "index",
            fallback = "handlerFallback", fallbackClass = {SentinelFallback.class},
            blockHandler = "blockHandler", blockHandlerClass = {SentinelFallback.class})
    public JSONResult index() {
        log.info("认证服务接口，url：/auth/index，端口号：" + port);
        return JSONResult.message(HttpStatus.HTTP_OK, "认证服务", "端口号：" + port);
    }

    /**
     * 登录认证
     *
     * @param username
     * @return
     */
    @Trace
    @Tag(key = "loginAuth", value = "username")
    @LKAMethod(value = "loginAuth 方法", description = "查询库存，URL：/auth/loginAuth/{username}")
    @LKAParam(name = "username", value = "用户名")
    @GetMapping("/auth/loginAuth/{username}")
    public JSONResult loginAuth(@PathVariable("username") String username) {
        Users users = dataLayerAuthService.loginAuthDatalayer(username);
        if (users == null) {
            return JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "登录认证失败，端口号：" + port);
        }
        return JSONResult.message(HttpStatus.HTTP_OK, "登录认证成功，端口号：" + port, users);
    }
}

package cloud.liupeng.openfeign.service.datalayer;

import cloud.liupeng.domain.entity.auth.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * openFeign 提供认证服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-dubbo-consumer-auth")
public interface DataLayerAuthService {

    /**
     * 登录认证
     *
     * @param username
     * @return
     */
    @PostMapping("/loginAuthDatalayer/{username}")
    Users loginAuthDatalayer(@PathVariable("username") String username);
}

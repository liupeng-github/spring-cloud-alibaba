package cloud.liupeng.openfeign.service.auth;

import cloud.liupeng.api.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 提供服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-service-auth", path = "/auth", url = "http://127.0.0.1:2020")
public interface AuthService {

    /**
     * 账户认证
     *
     * @param userId
     * @return
     */
    @GetMapping("/accountAuth/{userId}")
    JSONResult accountAuth(@PathVariable("userId") String userId);

    @GetMapping("/loginAuth/{username}")
    JSONResult loginAuth(@PathVariable("username") String username);
}

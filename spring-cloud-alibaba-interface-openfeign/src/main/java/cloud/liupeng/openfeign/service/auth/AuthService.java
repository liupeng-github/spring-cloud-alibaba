package cloud.liupeng.openfeign.service.auth;

import cloud.liupeng.api.utils.JsonResult;
import cloud.liupeng.openfeign.constant.ConstantService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 提供服务接口
 *
 * @author liupeng
 */
@FeignClient(name = ConstantService.SPRING_CLOUD_ALIBABA_SERVICE_AUTH, path = "/auth")
public interface AuthService {

    /**
     * 账户认证
     *
     * @param userId
     * @return
     */
    @GetMapping("/accountAuth/{userId}")
    JsonResult accountAuth(@PathVariable("userId") String userId);

    @GetMapping("/loginAuth/{username}")
    JsonResult loginAuth(@PathVariable("username") String username);
}

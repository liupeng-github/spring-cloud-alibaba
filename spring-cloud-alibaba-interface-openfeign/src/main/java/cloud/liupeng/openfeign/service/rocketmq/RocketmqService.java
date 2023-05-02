package cloud.liupeng.openfeign.service.rocketmq;

import cloud.liupeng.api.utils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 提供服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-service-rocketmq", path = "/rocketmq")
public interface RocketmqService {

    @GetMapping("/rocketMessage/{message}")
    JsonResult rocketMessage(@PathVariable("message") String message);
}

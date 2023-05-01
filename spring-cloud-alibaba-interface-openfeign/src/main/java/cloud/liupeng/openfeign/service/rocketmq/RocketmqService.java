package cloud.liupeng.openfeign.service.rocketmq;

import cloud.liupeng.api.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 提供服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-service-rocketmq", path = "/rocketmq", url = "http://127.0.0.1:3020")
public interface RocketmqService {

    @GetMapping("/rocketMessage/{message}")
    JSONResult rocketMessage(@PathVariable("message") String message);
}

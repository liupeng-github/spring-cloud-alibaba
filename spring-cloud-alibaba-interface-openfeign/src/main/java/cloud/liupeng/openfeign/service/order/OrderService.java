package cloud.liupeng.openfeign.service.order;

import cloud.liupeng.api.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 提供服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-service-order", path = "/order", url = "http://127.0.0.1:2022")
public interface OrderService {

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    @GetMapping("/getOrder/{orderId}")
    JSONResult getOrder(@PathVariable("orderId") Long orderId);
}

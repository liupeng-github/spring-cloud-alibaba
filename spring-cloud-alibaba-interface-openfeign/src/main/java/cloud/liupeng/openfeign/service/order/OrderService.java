package cloud.liupeng.openfeign.service.order;

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
@FeignClient(name = ConstantService.SPRING_CLOUD_ALIBABA_SERVICE_ORDER, path = "/order")
public interface OrderService {

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    @GetMapping("/getOrder/{orderId}")
    JsonResult getOrder(@PathVariable("orderId") Long orderId);
}

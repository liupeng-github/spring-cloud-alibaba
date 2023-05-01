package cloud.liupeng.openfeign.service.datalayer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * openFeign 提供支付服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-dubbo-consumer-pay")
public interface DataLayerPayService {

    /**
     * 订单支付
     *
     * @param orderId
     * @return
     */
    @PostMapping("/orderPayDatalayer/{orderId}")
    Integer orderPayDatalayer(@PathVariable("orderId") Long orderId);
}

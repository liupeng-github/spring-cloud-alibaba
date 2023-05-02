package cloud.liupeng.openfeign.service.datalayer;

import cloud.liupeng.openfeign.constant.ConstantService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * openFeign 提供支付服务接口
 *
 * @author liupeng
 */
@FeignClient(name = ConstantService.SPRING_CLOUD_ALIBABA_DUBBO_CONSUMER_PAY)
public interface DataLayerPayService {

    /**
     * 订单支付
     *
     * @param orderId
     * @return
     */
    @PostMapping("/orderPayDatalayer/{orderId}")
    int orderPayDatalayer(@PathVariable("orderId") Long orderId);
}

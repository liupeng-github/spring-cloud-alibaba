package cloud.liupeng.openfeign.service.pay;

import cloud.liupeng.api.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 提供服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-service-pay", path = "/pay", url = "http://127.0.0.1:2024")
public interface PayService {

    @GetMapping("/orderPay/{commodityCode}")
    JSONResult orderPay(@PathVariable("commodityCode") String commodityCode);
}

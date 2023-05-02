package cloud.liupeng.openfeign.service.pay;

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
@FeignClient(name = ConstantService.SPRING_CLOUD_ALIBABA_SERVICE_PAY, path = "/pay")
public interface PayService {

    @GetMapping("/orderPay/{commodityCode}")
    JsonResult orderPay(@PathVariable("commodityCode") String commodityCode);
}

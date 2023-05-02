package cloud.liupeng.openfeign.service.logistics;

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
@FeignClient(name = ConstantService.SPRING_CLOUD_ALIBABA_SERVICE_LOGISTICS, path = "/logistics")
public interface LogisticsService {

    /**
     * 物流单号查询物流
     *
     * @param logistics_id
     * @return
     */
    @GetMapping("/getLogistics/{logistics_id}")
    JsonResult getLogistics(@PathVariable("logistics_id") String logistics_id);
}

package cloud.liupeng.openfeign.service.logistics;

import cloud.liupeng.api.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 提供服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-service-logistics", path = "/logistics", url = "http://127.0.0.1:2025")
public interface LogisticsService {

    /**
     * 物流单号查询物流
     *
     * @param logistics_id
     * @return
     */
    @GetMapping("/getLogistics/{logistics_id}")
    JSONResult getLogistics(@PathVariable("logistics_id") String logistics_id);
}

package cloud.liupeng.openfeign.service.datalayer;

import cloud.liupeng.domain.entity.logistics.Logistics;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * openFeign 提供物流服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-dubbo-consumer-logistics")
public interface DataLayerLogisticsService {

    /**
     * 查询物流
     *
     * @param id
     * @return
     */
    @PostMapping("/getLogisticsDatalayer/{logisticsId}")
    Logistics getLogisticsDatalayer(@PathVariable("logisticsId") Integer id);
}

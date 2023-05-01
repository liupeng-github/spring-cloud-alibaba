package cloud.liupeng.openfeign.service.datalayer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * openFeign 提供仓储服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-dubbo-consumer-storage")
public interface DataLayerStorageService {

    /**
     * 查询商品
     *
     * @param commodityCode
     * @return
     */
    @PostMapping("/getStorageDatalayer/{commodityCode}")
    Integer getStorageDatalayer(@PathVariable("commodityCode") String commodityCode);

    /**
     * 减库存
     *
     * @param commodityCode
     * @return
     */
    @PostMapping("/deductDatalayer/{commodityCode}/{count}")
    Integer deductDatalayer(@PathVariable("commodityCode") String commodityCode, @PathVariable("count") int count);
}

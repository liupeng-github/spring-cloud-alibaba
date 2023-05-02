package cloud.liupeng.openfeign.service.datalayer;

import cloud.liupeng.openfeign.constant.ConstantService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * openFeign 提供仓储服务接口
 *
 * @author liupeng
 */
@FeignClient(name = ConstantService.SPRING_CLOUD_ALIBABA_DUBBO_CONSUMER_STORAGE)
public interface DataLayerStorageService {

    /**
     * 查询商品
     *
     * @param commodityCode
     * @return
     */
    @PostMapping("/getStorageTotalDatalayer/{commodityCode}")
    int getStorageTotalDatalayer(@PathVariable("commodityCode") String commodityCode);

    /**
     * 减库存
     *
     * @param commodityCode
     * @return
     */
    @PostMapping("/deductDatalayer/{commodityCode}/{count}")
    int deductDatalayer(@PathVariable("commodityCode") String commodityCode, @PathVariable("count") int count);
}

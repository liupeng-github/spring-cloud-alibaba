package cloud.liupeng.openfeign.service.storage;

import cloud.liupeng.api.utils.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 提供服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-service-storage", path = "/storage", url = "http://127.0.0.1:2021")
public interface StorageService {

    /**
     * 查询库存
     *
     * @param commodityCode
     * @return
     */
    @GetMapping("/getStorage/{commodityCode}")
    JSONResult getStorage(@PathVariable("commodityCode") String commodityCode);

    /**
     * 仓储减库存
     *
     * @param commodityCode
     * @param count
     * @return
     */
    @GetMapping("/deduct/{commodityCode}/{count}")
    JSONResult deduct(@PathVariable("commodityCode") String commodityCode, @PathVariable("count") int count);
}

package cloud.liupeng.openfeign.service.storage;

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
@FeignClient(name = ConstantService.SPRING_CLOUD_ALIBABA_SERVICE_STORAGE, path = "/storage")
public interface StorageService {

    /**
     * 查询库存
     *
     * @param commodityCode
     * @return
     */
    @GetMapping("/getStorage/{commodityCode}")
    JsonResult getStorage(@PathVariable("commodityCode") String commodityCode);

    /**
     * 仓储减库存
     *
     * @param commodityCode
     * @param count
     * @return
     */
    @GetMapping("/deduct/{commodityCode}/{count}")
    JsonResult deduct(@PathVariable("commodityCode") String commodityCode, @PathVariable("count") int count);
}

package cloud.liupeng.consumer.controller;

import cloud.liupeng.dubbo.storage.IStorageApacheService;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据消费层，仓储接口
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "StorageController", description = "仓储接口，Dubbo 消费服务，端口号：1121")
@RestController
public class StorageController {

    @DubboReference
    private IStorageApacheService storageApacheService;

    /**
     * 查询商品
     *
     * @param commodityCode
     * @return
     */
    @Trace
    @Tag(key = "getStorageTotalDatalayer", value = "commodityCode")
    @LKAMethod(value = "getStorageTotalDatalayer 方法", description = "查询商品")
    @LKAParam(name = "commodityCode", value = "商品编号")
    @PostMapping("/getStorageTotalDatalayer/{commodityCode}")
    public int getStorageTotalDatalayer(@PathVariable("commodityCode") String commodityCode) {
        return storageApacheService.getStorageTotal(commodityCode);
    }

    /**
     * 减库存
     *
     * @param commodityCode
     * @param count
     * @return
     */
    @Trace
    @Tag(key = "deductDatalayer", value = "commodityCode")
    @LKAMethod(value = "deductDatalayer 方法", description = "减库存")
    @LKAParam(names = {"commodityCode", "count"}, values = {"商品编号", "数量"})
    @PostMapping("/deductDatalayer/{commodityCode}/{count}")
    public int deductDatalayer(@PathVariable("commodityCode") String commodityCode, @PathVariable("count") int count) {
        return storageApacheService.deduct(commodityCode, count);
    }
}

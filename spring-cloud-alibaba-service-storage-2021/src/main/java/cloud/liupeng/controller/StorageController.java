package cloud.liupeng.controller;

import cloud.liupeng.api.sentinel.SentinelFallback;
import cloud.liupeng.api.utils.JsonResult;
import cloud.liupeng.openfeign.service.datalayer.DataLayerStorageService;
import cn.hutool.http.HttpStatus;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * 仓储服务
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "StorageController", description = "仓储服务，端口号：2021")
@RestController
public class StorageController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private DataLayerStorageService dataLayerStorageService;

    @Trace
    @LKAMethod(value = "index 方法", description = "单体服务测试方法")
    @GetMapping("/storage/index")
    @SentinelResource(value = "index",
            fallback = "handlerFallback", fallbackClass = {SentinelFallback.class},
            blockHandler = "blockHandler", blockHandlerClass = {SentinelFallback.class})
    public JsonResult index() {
        log.info("仓储服务接口，url：/storage/index，端口号：" + port);
        return JsonResult.success(HttpStatus.HTTP_OK, "仓储服务", "端口号：" + port);
    }

    /**
     * 查询库存
     *
     * @param commodityCode
     * @return
     */
    @Trace
    @Tag(key = "getStorage", value = "commodityCode")
    @LKAMethod(value = "getStorage 方法", description = "查询库存，URL：/storage/getStorage/{commodityCode}")
    @LKAParam(name = "commodityCode", value = "商品编号")
    @GetMapping("/storage/getStorageTotal/{commodityCode}")
    public JsonResult getStorageTotal(@PathVariable("commodityCode") String commodityCode) throws ExecutionException, InterruptedException {
        int storageTotal = dataLayerStorageService.getStorageTotalDatalayer(commodityCode);
        if (storageTotal == 0) {
            return JsonResult.success(HttpStatus.HTTP_NO_CONTENT, "库存不足", storageTotal);
        }
        return JsonResult.success(HttpStatus.HTTP_OK, "查询库存接口，url：/storage/getStorage，参数：" + commodityCode, storageTotal);
    }

    /**
     * 仓储减库存
     *
     * @param commodityCode
     * @param count
     * @return
     */
    @Trace
    @Tag(key = "deduct", value = "commodityCode")
    @LKAMethod(value = "deduct 方法", description = "仓储减库存，URL：/storage/deduct/{commodityCode}/{count}")
    @LKAParam(names = {"commodityCode", "count"}, values = {"商品编号", "count"})
    @GetMapping("/storage/deduct/{commodityCode}/{count}")
    public JsonResult deduct(@PathVariable("commodityCode") String commodityCode, @PathVariable("count") int count) {
        int deduct = dataLayerStorageService.deductDatalayer(commodityCode, count);
        if (deduct == 0) {
            return JsonResult.success(HttpStatus.HTTP_OK, "数据层仓储服务，deduct 方法，端口号：" + port, deduct);
        }
        return JsonResult.success(HttpStatus.HTTP_NO_CONTENT, "数据层仓储服务，deduct 方法，减库存失败，端口号：" + port, deduct);
    }
}

package cloud.liupeng.controller;

import cloud.liupeng.api.sentinel.SentinelFallback;
import cloud.liupeng.api.utils.JSONResult;
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
    @GetMapping("/index")
    @SentinelResource(value = "index",
            fallback = "handlerFallback", fallbackClass = {SentinelFallback.class},
            blockHandler = "blockHandler", blockHandlerClass = {SentinelFallback.class})
    public JSONResult index() {
        log.info("仓储服务接口，url：/storage/index，端口号：" + port);
        return JSONResult.message(HttpStatus.HTTP_OK, "仓储服务", "端口号：" + port);
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
    @GetMapping("/storage/getStorage/{commodityCode}")
    public JSONResult getStorage(@PathVariable("commodityCode") String commodityCode) throws ExecutionException, InterruptedException {
        Integer storageDatalayer = dataLayerStorageService.getStorageDatalayer(commodityCode);
        if (storageDatalayer == 0) {
            return JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "库存不足");
        }
        return JSONResult.message(HttpStatus.HTTP_OK, "查询库存接口，url：/storage/getStorage，参数：" + commodityCode, 1);
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
    public JSONResult deduct(@PathVariable("commodityCode") String commodityCode, @PathVariable("count") int count) {
        Integer deduct = dataLayerStorageService.deductDatalayer(commodityCode, count);
        if (deduct > 0) {
            return JSONResult.message(HttpStatus.HTTP_OK, "数据层仓储服务，deduct 方法，端口号：" + port, deduct);
        }
        return JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "数据层仓储服务，deduct 方法，减库存失败，端口号：" + port);
    }
}

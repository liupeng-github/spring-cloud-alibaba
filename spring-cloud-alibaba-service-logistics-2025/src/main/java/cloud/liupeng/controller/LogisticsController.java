package cloud.liupeng.controller;

import cloud.liupeng.api.utils.JSONResult;
import cloud.liupeng.api.sentinel.SentinelFallback;
import cloud.liupeng.domain.entity.logistics.Logistics;
import cloud.liupeng.openfeign.service.datalayer.DataLayerLogisticsService;
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

/**
 * 物流服务调数据层
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "LogisticsController", description = "物流服务调数据层，端口号：2025")
@RestController
public class LogisticsController {

    @Autowired
    private DataLayerLogisticsService dataLayerLogisticsService;

    @Value("${server.port}")
    private String port;

    @Trace
    @LKAMethod(value = "index 方法", description = "单体服务测试方法")
    @GetMapping("/index")
    @SentinelResource(value = "index",
            fallback = "handlerFallback", fallbackClass = {SentinelFallback.class},
            blockHandler = "blockHandler", blockHandlerClass = {SentinelFallback.class})
    public JSONResult index() {
        log.info("物流服务接口，url：/logistics/index，端口号：" + port);
        return JSONResult.message(HttpStatus.HTTP_OK, "物流服务", "端口号：" + port);
    }

    /**
     * 物流单号查询物流
     *
     * @param id
     * @return
     */
    @Trace
    @Tag(key = "getLogistics", value = "logisticsId")
    @LKAMethod(value = "getLogistics 方法", description = "物流单号查询物流，URL：/logistics/getLogistics/{logisticsId}")
    @LKAParam(name = "id", value = "物流编号")
    @GetMapping("/logistics/getLogistics/{id}")
    public JSONResult getLogistics(@PathVariable("id") Integer id) {
        log.info("查询物流接口，url：/logistics/getLogistics/，参数：" + id);
        Logistics logisticsDatalayer = dataLayerLogisticsService.getLogisticsDatalayer(id);
        if (logisticsDatalayer == null) {
            return JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "暂时未查到物流信息");
        }
        return JSONResult.message(HttpStatus.HTTP_OK, "物流单号查询物流", logisticsDatalayer);
    }
}

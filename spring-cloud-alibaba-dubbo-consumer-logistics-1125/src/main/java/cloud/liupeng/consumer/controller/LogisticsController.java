package cloud.liupeng.consumer.controller;

import cloud.liupeng.domain.entity.logistics.Logistics;
import cloud.liupeng.dubbo.logistics.ILogisticsApacheService;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据消费层，物流接口
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "LogisticsController", description = "物流接口，Dubbo 消费服务，端口号：1125")
@RestController
public class LogisticsController {

    @Value("${server.port}")
    private String port;

    @DubboReference
    private ILogisticsApacheService logisticsApacheService;

    /**
     * 查询物流
     *
     * @param id
     * @return
     */
    @Trace
    @Tag(key = "getLogisticsDatalayer", value = "logisticsId")
    @LKAMethod(value = "getLogisticsDatalayer 方法", description = "查询物流")
    @LKAParam(name = "logisticsId", value = "物流编号")
    @PostMapping("/getLogisticsDatalayer/{logisticsId}")
    public Logistics getLogisticsDatalayer(@PathVariable("logisticsId") Integer id) {
        return logisticsApacheService.getLogisticsDatalayer(id);
    }
}

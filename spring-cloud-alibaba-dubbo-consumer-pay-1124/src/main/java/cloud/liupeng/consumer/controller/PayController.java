package cloud.liupeng.consumer.controller;

import cloud.liupeng.dubbo.pay.IPayApacheService;
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
 * 数据消费层，支付接口
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "PayController", description = "支付接口，Dubbo 消费服务，端口号：1124")
@RestController
public class PayController {

    @DubboReference
    private IPayApacheService payApacheService;

    /**
     * 订单支付
     *
     * @param orderId
     * @return
     */
    @Trace
    @Tag(key = "orderPayDatalayer", value = "commodityCode")
    @LKAMethod(value = "orderPayDatalayer 方法", description = "订单支付")
    @LKAParam(name = "orderId", value = "订单编号")
    @PostMapping("/orderPayDatalayer/{orderId}")
    public int orderPayDatalayer(@PathVariable("orderId") Long orderId) {
        return payApacheService.orderPayDatalayer(orderId);
    }
}

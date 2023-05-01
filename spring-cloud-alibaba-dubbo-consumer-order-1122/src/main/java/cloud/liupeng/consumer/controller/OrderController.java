package cloud.liupeng.consumer.controller;

import cloud.liupeng.domain.entity.order.Order;
import cloud.liupeng.dubbo.order.IOrderApacheService;
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
 * 数据消费层，订单接口
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "OrderController", description = "订单接口，Dubbo 消费服务，端口号：1122")
@RestController
public class OrderController {

    @DubboReference
    private IOrderApacheService orderApacheService;

    @Value("${server.port}")
    private String port;

    /**
     * 创建订单
     *
     * @param commodityCode
     * @return
     */
    @Trace
    @Tag(key = "createOrderDatalayer", value = "commodityCode")
    @LKAMethod(value = "createOrderDatalayer 方法", description = "创建订单")
    @LKAParam(names = {"userId", "commodityCode", "orderCount"}, values = {"用户ID", "商品编号", "订单数量"})
    @PostMapping("/createOrderDatalayer/{userId}/{commodityCode}/{orderCount}")
    public Integer createOrderDatalayer(@PathVariable("userId") String userId, @PathVariable("commodityCode") String commodityCode, @PathVariable("orderCount") Integer orderCount) {
        return orderApacheService.create(userId, commodityCode, orderCount);
    }

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    @Trace
    @Tag(key = "getOrderDatalayer", value = "orderId")
    @LKAMethod(value = "getOrderDatalayer 方法", description = "查询订单")
    @LKAParam(name = "orderId", value = "订单ID")
    @PostMapping("/getOrderDatalayer/{orderId}")
    public Order getOrderDatalayer(@PathVariable("orderId") Long orderId) {
        return orderApacheService.getOrder(orderId);
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param status
     * @return
     */
    @PostMapping("/updateOrderStatus/{orderId}/{status}")
    Order updateOrderStatus(@PathVariable("orderId") Long orderId, @PathVariable("status") int status) {
        return orderApacheService.updateOrderStatus(orderId, status);
    }
}

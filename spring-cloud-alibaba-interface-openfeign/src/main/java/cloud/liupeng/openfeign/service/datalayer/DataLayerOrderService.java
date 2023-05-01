package cloud.liupeng.openfeign.service.datalayer;

import cloud.liupeng.domain.entity.order.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * openFeign 提供订单服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-dubbo-consumer-order")
public interface DataLayerOrderService {

    /**
     * 创建订单
     *
     * @param commodityCode
     * @param orderCount
     * @return
     */
    @PostMapping("/createOrderDatalayer/{userId}/{commodityCode}/{orderCount}")
    Integer createOrderDatalayer(@PathVariable("userId") String userId, @PathVariable("commodityCode") String commodityCode, @PathVariable("orderCount") Integer orderCount);

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    @PostMapping("/getOrderDatalayer/{orderId}")
    Order getOrderDatalayer(@PathVariable("orderId") Long orderId);

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param status
     * @return
     */
    @PostMapping("/updateOrderStatus/{orderId}/{status}")
    Order updateOrderStatus(@PathVariable("orderId") Long orderId, @PathVariable("status") int status);
}

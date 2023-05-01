package cloud.liupeng.dubbo.order;

import cloud.liupeng.domain.entity.order.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ApacheDubbo 提供数据层订单接口
 *
 * @author liupeng
 */
public interface IOrderApacheService extends IService<Order> {

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    Order getOrder(Long orderId);

    /**
     * 创建订单
     */
    Integer create(String userId, String commodityCode, int orderCount);

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param status
     * @return
     */
    Order updateOrderStatus(Long orderId, int status);
}

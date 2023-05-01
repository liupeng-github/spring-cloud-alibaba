package cloud.liupeng.provider.order.service;

import cloud.liupeng.api.utils.SnowFlake;
import cloud.liupeng.dubbo.order.IOrderApacheService;
import cloud.liupeng.domain.entity.order.Order;
import cloud.liupeng.provider.order.mapper.OrderMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 数据层订单接口实现类
 *
 * @author liupeng
 */
@DS("order")
@DubboService
@LKAType(value = "OrderApacheServiceImpl", description = "数据层订单接口实现类，Dubbo 提供服务，端口号：1022")
public class OrderApacheServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderApacheService {

    private int calculate(String commodityId, int orderCount) {
        return 200 * orderCount;
    }

    /**
     * 创建订单
     *
     * @param userId
     * @param commodityCode
     * @param orderCount
     * @return
     */
    @LKAMethod(value = "create 方法", description = "create 方法，数据层订单接口实现类，创建订单")
    @LKAParam(names = {"userId", "money"}, values = {"用户ID", "金额"})
    @Override
    public Integer create(String userId, String commodityCode, int orderCount) {

        int orderMoney = calculate(commodityCode, orderCount);

        Order order = new Order();
        order.setId(new SnowFlake(1, 1, 1).nextId());
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(orderCount);
        order.setMoney(orderMoney);
        order.setStatus(0);

        // INSERT INTO orders ...
        return baseMapper.insert(order);
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param status
     * @return
     */
    @Override
    public Order updateOrderStatus(Long orderId, int status) {
        return baseMapper.updateOrderStatus(orderId, status);
    }

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    @LKAMethod(value = "getOrder 方法", description = "getOrder 方法，数据层订单接口实现类，查询订单")
    @LKAParam(name = "orderId", value = "订单ID")
    @Override
    public Order getOrder(Long orderId) {
        return baseMapper.getOrder(orderId);
    }
}

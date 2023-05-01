package cloud.liupeng.controller;

import cloud.liupeng.api.constant.BusinessConstant;
import cloud.liupeng.domain.entity.order.Order;
import cloud.liupeng.api.sentinel.SentinelFallback;
import cloud.liupeng.api.utils.JSONResult;
import cloud.liupeng.openfeign.service.datalayer.DataLayerOrderService;
import cloud.liupeng.openfeign.service.datalayer.DataLayerStorageService;
import cn.hutool.http.HttpStatus;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单服务
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "OrderController", description = "订单服务，端口号：2022")
@RestController
public class OrderController {

    @Autowired
    private DataLayerOrderService dataLayerOrderService;

    @Autowired
    private DataLayerStorageService dataLayerStorageService;

    @Value("${server.port}")
    private String port;

    @Trace
    @GetMapping("/index")
    @LKAMethod(value = "index 方法", description = "单体服务测试方法")
    @SentinelResource(value = "index",
            fallback = "handlerFallback", fallbackClass = {SentinelFallback.class},
            blockHandler = "blockHandler", blockHandlerClass = {SentinelFallback.class})
    public JSONResult index() {
        log.info("订单服务接口，url：/order/index，端口号：{}", port + "，线程名：" + Thread.currentThread().getName());
        return JSONResult.message(HttpStatus.HTTP_OK, "订单服务", "端口号：" + port);
    }

    /**
     * MQ 接收用户请求，异步创建订单 --> 减库存 --> 扣余额 --> 改(订单)状态
     *
     * @return
     */
    @Async("customThreadPool")
    @Trace
    @Tag(key = "createOrder", value = "commodityCode")
    @LKAMethod(value = "createOrder 方法", description = "创建订单，URL：/order/createOrder/{commodityCode}/{orderCount}")
    @LKAParam(names = {"commodityCode", "orderCount"}, values = {"商品编号", "订单量"})
    @GlobalTransactional(name = "create-order", rollbackFor = Exception.class)
    @GetMapping("/order/createOrder/{commodityCode}/{orderCount}")
    public JSONResult createOrder(@PathVariable("commodityCode") String commodityCode, @PathVariable("orderCount") Integer orderCount) {

        log.info("查询库存接口，商品编号：{}", commodityCode);
        Integer storageDatalayer = dataLayerStorageService.getStorageDatalayer(commodityCode);
        if (storageDatalayer == 0) {
            return JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "商品库存不足，订单创建失败！");
        }

        log.info("减库存接口，商品编号：{}", commodityCode, "，订单量：{}", orderCount);
        Integer deduct = dataLayerStorageService.deductDatalayer(commodityCode, orderCount);
        if (deduct == 0) {
            return JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "减库存失败，订单创建失败！");
        }

        log.info("创建订单接口，用户ID：{}", BusinessConstant.USER_ID, "，商品编号：{}", commodityCode, "，订单量：{}", orderCount);
        Integer createOrderDatalayer = dataLayerOrderService.createOrderDatalayer(BusinessConstant.USER_ID, commodityCode, orderCount);
        if (createOrderDatalayer == 0) {
            return JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "订单创建失败！");
        }
        return JSONResult.message(HttpStatus.HTTP_OK, "成功创建订单", "订单：" + createOrderDatalayer);
    }

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    @Trace
    @Tag(key = "getOrder", value = "orderId")
    @LKAMethod(value = "getOrder 方法", description = "查询订单，URL：/order/getOrder/{orderId}")
    @LKAParam(name = "orderId", value = "订单号")
    @GetMapping("/order/getOrder/{orderId}")
    public JSONResult getOrder(@PathVariable("orderId") Long orderId) {

        Order orderDatalayer = dataLayerOrderService.getOrderDatalayer(orderId);
        if (orderDatalayer == null) {
            return JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "暂时未查询到该订单");
        }
        return JSONResult.message(HttpStatus.HTTP_OK, "查询订单接口，url：/order/getOrder，参数：" + orderId, orderDatalayer);
    }
}

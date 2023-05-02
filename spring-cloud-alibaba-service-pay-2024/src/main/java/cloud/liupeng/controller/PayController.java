package cloud.liupeng.controller;

import cloud.liupeng.api.constant.BusinessConstant;
import cloud.liupeng.api.sentinel.SentinelFallback;
import cloud.liupeng.api.utils.JsonResult;
import cloud.liupeng.domain.entity.order.Order;
import cloud.liupeng.openfeign.service.datalayer.DataLayerAccountService;
import cloud.liupeng.openfeign.service.datalayer.DataLayerOrderService;
import cloud.liupeng.openfeign.service.datalayer.DataLayerPayService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付服务
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "PayController", description = "支付服务，端口号：2024")
@RestController
public class PayController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private DataLayerPayService dataLayerPayService;

    @Autowired
    private DataLayerAccountService dataLayerAccountService;

    @Autowired DataLayerOrderService dataLayerOrderService;

    @Trace
    @LKAMethod(value = "index 方法", description = "单体服务测试方法")
    @GetMapping("/pay/index")
    @SentinelResource(value = "index",
            fallback = "handlerFallback", fallbackClass = {SentinelFallback.class},
            blockHandler = "blockHandler", blockHandlerClass = {SentinelFallback.class})
    public JsonResult index() {
        log.info("支付服务接口，url：/pay/index，端口号：" + port);
        return JsonResult.success(HttpStatus.HTTP_OK, "支付服务", "端口号：" + port);
    }

    /**
     * 订单支付
     *
     * @param orderId
     * @return
     */
    @Trace
    @Tag(key = "orderPay", value = "orderId")
    @LKAMethod(value = "orderPay 方法", description = "订单支付，URL：/pay/orderPay/{orderId}/{money}")
    @LKAParam(names = {"orderId", "money"}, values = {"订单号", "金额"})
    @GlobalTransactional(name = "order-pay", rollbackFor = Exception.class)
    @GetMapping("/pay/orderPay/{orderId}/{money}")
    public JsonResult orderPay(@PathVariable("orderId") Long orderId, @PathVariable("money") Integer money) {

        log.info("订单支付接口，订单号：{}", orderId, "金额：{}", money);
        int orderPay = dataLayerPayService.orderPayDatalayer(orderId);
        if (orderPay == 0){
            return JsonResult.success(HttpStatus.HTTP_NO_CONTENT, "订单支付失败");
        }

        log.info("扣除账户，用户ID：{}", BusinessConstant.USER_ID, "金额：{}", money);
        int debited = dataLayerAccountService.debitDatalayer(BusinessConstant.USER_ID, money);
        if (debited == 0){
            return JsonResult.success(HttpStatus.HTTP_NO_CONTENT, "订单扣除账户失败");
        }

        log.info("修改订单状态，订单号：{}", orderId, "订单状态：{}", BusinessConstant.STATUS);
        Order order = dataLayerOrderService.updateOrderStatus(orderId, BusinessConstant.STATUS);

        if (order == null) {
            return JsonResult.success(HttpStatus.HTTP_NO_CONTENT, "订单状态修改失败");
        }
        return JsonResult.success(HttpStatus.HTTP_OK, "订单支付成功", orderPay);
    }
}

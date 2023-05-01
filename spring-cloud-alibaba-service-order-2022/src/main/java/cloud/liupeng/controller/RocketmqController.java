package cloud.liupeng.controller;

import cloud.liupeng.api.utils.JSONResult;
import cloud.liupeng.openfeign.service.rocketmq.RocketmqService;
import cn.hutool.http.HttpStatus;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liupeng
 */
@Slf4j
@LKAType(value = "RocketmqController", description = "订单调消息服务，端口号：2022")
@RestController
public class RocketmqController {

    @Autowired
    private RocketmqService rocketmqService;

    /**
     * 订单调消息服务
     *
     * @return
     */
    @Trace
    @Tag(key = "rocketMessage", value = "message")
    @LKAMethod(value = "rocketMessage 方法", description = "订单调消息服务，URL：/order/rocketMessage/{orderId}")
    @LKAParam(name = "message", value = "消息")
    @GetMapping("/order/rocketMessage/{message}")
    public JSONResult rocketMessage(@PathVariable("message") String message) {
        log.info("调消息服务接口，url：/order/rocketmq/，参数：" + message);
        return JSONResult.message(HttpStatus.HTTP_OK, "订单调消息服务", rocketmqService.rocketMessage(message));
    }
}

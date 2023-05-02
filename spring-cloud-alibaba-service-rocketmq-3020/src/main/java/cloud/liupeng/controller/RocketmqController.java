package cloud.liupeng.controller;

import cloud.liupeng.api.utils.JsonResult;
import cn.hutool.http.HttpStatus;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息服务
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "RocketmqController", description = "RocketMQ 服务，端口号：2321")
@RestController
public class RocketmqController {

    @Value("${server.port}")
    private String port;

    @Trace
    @LKAMethod(value = "index 方法", description = "单体服务测试方法")
    @GetMapping("/rocketmq/index")
    public JsonResult index() {
        log.info("RocketMQ 服务接口，url：/rocketmq/index，端口号：" + port);
        return JsonResult.success(HttpStatus.HTTP_OK, "RocketMQ 服务", "端口号：" + port);
    }

    /**
     * RocketMQ
     *
     * @param message
     * @return
     */
    @Trace
    @Tag(key = "rocketMessage", value = "message")
    @LKAMethod(value = "rocketMessage 方法", description = "RocketMQ 接收消息，URL：/rocketmq/rocketMessage/{message}")
    @LKAParam(name = "message", value = "消息")
    @GetMapping("/rocketmq/rocketMessage/{message}")
    public JsonResult rocketMessage(@PathVariable("message") String message) {
        log.info("订单支付接口，url：/rocketmq/rocketMessage/，参数：" + message);
        return JsonResult.success(HttpStatus.HTTP_OK, "RocketMQ 消息", "端口号：" + port);
    }
}

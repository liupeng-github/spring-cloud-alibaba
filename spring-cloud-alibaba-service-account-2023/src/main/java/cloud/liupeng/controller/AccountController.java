package cloud.liupeng.controller;

import cloud.liupeng.api.annotations.Jmh;
import cloud.liupeng.domain.entity.account.Account;
import cloud.liupeng.api.utils.JSONResult;
import cloud.liupeng.openfeign.service.datalayer.DataLayerAccountService;
import cloud.liupeng.api.sentinel.SentinelFallback;
import cn.hutool.http.HttpStatus;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import lombok.extern.log4j.Log4j2;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户调数据层服务
 *
 * @author liupeng
 */
@Jmh
@State(Scope.Benchmark) // 指定一个对象的作用范围
@Log4j2
@LKAType(value = "AccountController", description = "账户服务，调数据层接口，端口号：2023")
@RestController
public class AccountController {

    private static final String JMH_RESULT_ADDRESS = "/logs/springcloud-alibaba/springcloud-alibaba-business-account-2023/result.json";

    @Value("${server.port}")
    private String port;

    @Autowired
    private DataLayerAccountService dataLayerAccountService;

    /**
     * JMH 性能测试
     * @param args
     * @throws RunnerException
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(AccountController.class.getSimpleName())
                .result(JMH_RESULT_ADDRESS)
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }

    /**
     * blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，
     * 而 fallback 函数会针对所有类型的异常。
     *
     * @return
     */
    @Benchmark
    @Trace
    @LKAMethod(value = "index 方法", description = "单体服务测试方法")
    @GetMapping("/account/index")
    @SentinelResource(value = "index",
            fallback = "handlerFallback", fallbackClass = {SentinelFallback.class},
            blockHandler = "blockHandler", blockHandlerClass = {SentinelFallback.class})
    public JSONResult index() {
        log.info("账户服务接口，url：/account/index，端口号：" + port);
        return JSONResult.message(HttpStatus.HTTP_OK, "账户服务", "端口号：" + port);
    }

    /**
     * 查询账户信息
     *
     * @param userId
     * @return
     */
    @Trace
    @Tag(key = "getAccount", value = "userId")
    @LKAMethod(value = "getAccount 方法", description = "查询账户信息，URL：/account/getAccount/{userId}")
    @LKAParam(name = "userId", value = "用户ID")
    @GetMapping("/account/getAccount/{userId}")
    public JSONResult getAccount(@PathVariable("userId") String userId) {
        log.info("查询账户信息接口，url：/account/getAccount/，参数：{}", userId);
        Account accountDatalayer = dataLayerAccountService.getAccountDatalayer(userId);
        if (accountDatalayer == null) {
            JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "暂时未查询到该账户信息");
        }
        return JSONResult.message(HttpStatus.HTTP_OK, "查询账户信息，端口号：" + port, accountDatalayer);
    }

    /**
     * 减金额
     *
     * @param userId
     * @param money
     * @return
     */
    @Trace
    @Tag(key = "getAccount", value = "userId")
    @LKAMethod(value = "debit 方法", description = "减金额，URL：/account/debit/{userId}/{money}")
    @LKAParam(names = {"userId", "money"}, values = {"用户ID", "金额"})
    @GetMapping("/account/debit/{userId}/{money}")
    public JSONResult debit(@PathVariable("userId") String userId, @PathVariable("money") Integer money) {
        Integer debit = dataLayerAccountService.debitDatalayer(userId, money);
        if (debit > 0) {
            return JSONResult.message(HttpStatus.HTTP_OK, "减金额，端口号：" + port, debit);
        }
        return JSONResult.errorMsg(HttpStatus.HTTP_NO_CONTENT, "减金额失败，端口号：" + port);
    }
}

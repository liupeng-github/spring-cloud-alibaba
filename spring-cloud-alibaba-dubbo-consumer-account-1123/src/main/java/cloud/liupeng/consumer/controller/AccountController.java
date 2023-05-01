package cloud.liupeng.consumer.controller;

import cloud.liupeng.domain.entity.account.Account;
import cloud.liupeng.dubbo.account.IAccountApacheService;
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
 * 数据消费层，账户接口
 *
 * @author liupeng
 */
@Slf4j
@LKAType(value = "AccountController", description = "账户接口，Dubbo 消费服务，端口号：1123")
@RestController
public class AccountController {

    @DubboReference
    private IAccountApacheService accountApacheService;

    @Value("${server.port}")
    private String port;

    /**
     * 查询账户信息
     *
     * @param userId
     * @return
     */
    @Trace
    @Tag(key = "getAccountDatalayer", value = "userId")
    @LKAMethod(value = "getAccountDatalayer 方法", description = "查询账户信息")
    @LKAParam(name = "userId", value = "用户ID")
    @PostMapping("/getAccountDatalayer/{userId}")
    public Account getAccountDatalayer(@PathVariable("userId") String userId) {
        return accountApacheService.getByAccount(userId);
    }

    /**
     * 从用户账户中借出
     *
     * @param userId
     * @return
     */
    @Trace
    @Tag(key = "debitDatalayer", value = "userId")
    @LKAMethod(value = "debitDatalayer 方法", description = "从用户账户中借出")
    @LKAParam(names = {"userId", "money"}, values = {"用户ID", "金额"})
    @PostMapping("/debitDatalayer/{userId}/{money}")
    public Integer debitDatalayer(@PathVariable("userId") String userId, @PathVariable("money") Integer money) {
        return accountApacheService.debit(userId, money);
    }
}

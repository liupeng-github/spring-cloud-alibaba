package cloud.liupeng.openfeign.service.datalayer;

import cloud.liupeng.domain.entity.account.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * openFeign 提供账户服务接口
 *
 * @author liupeng
 */
@FeignClient(name = "spring-cloud-alibaba-dubbo-consumer-account")
public interface DataLayerAccountService {

    /**
     * 查询账户信息
     *
     * @param userId
     * @return
     */
    @PostMapping("/getAccountDatalayer/{userId}")
    Account getAccountDatalayer(@PathVariable("userId") String userId);

    /**
     * 减金额
     *
     * @param userId
     * @param money
     * @return
     */
    @PostMapping("/debitDatalayer/{userId}/{money}")
    Integer debitDatalayer(@PathVariable("userId") String userId, @PathVariable("money") Integer money);
}

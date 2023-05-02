package cloud.liupeng.openfeign.service.account;

import cloud.liupeng.api.utils.JsonResult;
import cloud.liupeng.openfeign.constant.ConstantService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * openFeign 提供服务接口
 *
 * @author liupeng
 */
@FeignClient(name = ConstantService.SPRING_CLOUD_ALIBABA_SERVICE_ACCOUNT, path = "/account")
public interface AccountService {

    /**
     * 查询账户信息
     *
     * @param account_id
     * @return
     */
    @GetMapping("/getAccount/{account_id}")
    JsonResult getAccount(@PathVariable("account_id") String account_id);

    /**
     * 从用户账户中借出
     *
     * @param userId
     * @param money
     */
    @GetMapping("/debit/{userId}/{money}")
    JsonResult debit(@PathVariable("userId") String userId, @PathVariable("money") Integer money);
}

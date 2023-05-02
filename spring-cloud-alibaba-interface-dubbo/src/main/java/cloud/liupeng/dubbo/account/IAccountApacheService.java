package cloud.liupeng.dubbo.account;

import cloud.liupeng.domain.entity.account.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ApacheDubbo 提供数据层账户接口
 *
 * @author liupeng
 */
public interface IAccountApacheService extends IService<Account> {

    /**
     * 查询用户账户
     *
     * @param userId
     * @return
     */
    Account getByAccount(String userId);

    /**
     * 从用户账户中借出
     */
    int debit(String userId, int money);
}

package cloud.liupeng.provider.account.service;

import cloud.liupeng.dubbo.account.IAccountApacheService;
import cloud.liupeng.domain.entity.account.Account;
import cloud.liupeng.provider.account.mapper.AccountMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据层账户接口实现类
 *
 * @author liupeng
 */
@DS("account")
@DubboService
@LKAType(value = "AccountApacheServiceImpl", description = "数据层账户接口实现类，Dubbo 提供服务，端口号：1023")
public class AccountApacheServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountApacheService {

    /**
     * 查询用户账户
     *
     * @param userId
     * @return
     */
    @LKAMethod(value = "getByAccount 方法", description = "数据层账户接口实现类，查询用户账户")
    @LKAParam(name = "userId", value = "用户ID")
    @Override
    public Account getByAccount(String userId) {
        return baseMapper.getByAccount(userId);
    }

    /**
     * 从用户账户中借出
     *
     * @param userId
     * @param money
     */
    @Transactional
    @LKAMethod(value = "debit 方法", description = "数据层账户接口实现类，从用户账户中借出")
    @LKAParam(names = {"userId", "money"}, values = {"用户ID", "金额"})
    @Override
    public Integer debit(String userId, int money) {
        return baseMapper.debit(userId, money);
    }
}

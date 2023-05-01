package cloud.liupeng.provider.auth.service;

import cloud.liupeng.domain.entity.auth.Users;
import cloud.liupeng.dubbo.auth.AuthApacheService;
import cloud.liupeng.provider.auth.mapper.AuthMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.api.annotation.LKAType;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 数据层认证接口实现类
 *
 * @author liupeng
 */
@DS("auth")
@DubboService
@LKAType(value = "AuthApacheServiceImpl", description = "数据层认证接口实现类，Dubbo 提供服务，端口号：1020")
public class AuthApacheServiceImpl extends ServiceImpl<AuthMapper, Users> implements AuthApacheService {

    @Override
    public Users loginAuthDatalayer(String username) {
        return baseMapper.loginAuth(username);
    }
}

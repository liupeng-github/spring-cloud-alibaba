package cloud.liupeng.dubbo.auth;

import cloud.liupeng.domain.entity.auth.Users;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ApacheDubbo 提供数据层认证接口
 *
 * @author liupeng
 */
public interface AuthApacheService {

    Users loginAuthDatalayer(@PathVariable("username") String username);
}

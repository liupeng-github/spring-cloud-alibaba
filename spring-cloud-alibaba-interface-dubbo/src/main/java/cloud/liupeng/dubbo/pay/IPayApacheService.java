package cloud.liupeng.dubbo.pay;

import cloud.liupeng.domain.entity.pay.Pay;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ApacheDubbo 提供数据层支付接口
 *
 * @author liupeng
 */
public interface IPayApacheService extends IService<Pay> {

    Integer orderPayDatalayer(@PathVariable("orderId") Long orderId);
}

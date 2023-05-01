package cloud.liupeng.provider.pay.service;

import cloud.liupeng.domain.entity.pay.Pay;
import cloud.liupeng.dubbo.pay.IPayApacheService;
import cloud.liupeng.provider.pay.mapper.PayMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.api.annotation.LKAType;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 数据层支付接口实现类
 *
 * @author liupeng
 */
@DS("pay")
@DubboService
@LKAType(value = "PayApacheServiceImpl", description = "数据层支付接口实现类，Dubbo 提供服务，端口号：1024")
public class PayApacheServiceImpl extends ServiceImpl<PayMapper, Pay> implements IPayApacheService {

    @Override
    public Integer orderPayDatalayer(Long orderId) {
        Pay pay = new Pay();
        pay.setOrderId(orderId);
        return baseMapper.insert(pay);
    }
}

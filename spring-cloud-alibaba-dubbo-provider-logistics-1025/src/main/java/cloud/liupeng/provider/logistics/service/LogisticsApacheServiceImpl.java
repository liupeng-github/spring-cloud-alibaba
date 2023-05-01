package cloud.liupeng.provider.logistics.service;

import cloud.liupeng.domain.entity.logistics.Logistics;
import cloud.liupeng.dubbo.logistics.ILogisticsApacheService;
import cloud.liupeng.provider.logistics.mapper.LogisticsMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.api.annotation.LKAType;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 数据层认证接口实现类
 *
 * @author liupeng
 */
@DS("logistics")
@DubboService
@LKAType(value = "LogisticsApacheServiceImpl", description = "数据层物流接口实现类，Dubbo 提供服务，端口号：1025")
public class LogisticsApacheServiceImpl extends ServiceImpl<LogisticsMapper, Logistics> implements ILogisticsApacheService {

    @Override
    public Logistics getLogisticsDatalayer(Integer id) {
        return baseMapper.getLogisticsDatalayer(id);
    }
}

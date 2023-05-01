package cloud.liupeng.dubbo.logistics;

import cloud.liupeng.domain.entity.logistics.Logistics;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ApacheDubbo 提供数据层物流接口
 *
 * @author liupeng
 */
public interface ILogisticsApacheService extends IService<Logistics> {

    Logistics getLogisticsDatalayer(@PathVariable("logisticsId") Integer id);
}

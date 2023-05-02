package cloud.liupeng.dubbo.storage;

import cloud.liupeng.domain.entity.storage.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ApacheDubbo 提供数据层仓储接口
 *
 * @author liupeng
 */
public interface IStorageApacheService extends IService<Storage> {

    /**
     * 查询商品
     */
    int getStorageTotal(String commodityCode);

    /**
     * 扣除商品存储数量
     */
    int deduct(String commodityCode, int count);
}

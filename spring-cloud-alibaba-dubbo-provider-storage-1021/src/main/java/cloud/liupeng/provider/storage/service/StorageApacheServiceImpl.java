package cloud.liupeng.provider.storage.service;

import cloud.liupeng.domain.entity.storage.Storage;
import cloud.liupeng.dubbo.storage.IStorageApacheService;
import cloud.liupeng.provider.storage.mapper.StorageMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lk.api.annotation.LKAMethod;
import com.lk.api.annotation.LKAParam;
import com.lk.api.annotation.LKAType;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 数据层仓储接口实现类
 *
 * @author liupeng
 */
@DS("storage")
@DubboService
@LKAType(value = "StorageApacheServiceImpl", description = "数据层仓储接口实现类，Dubbo 提供服务，端口号：1021")
public class StorageApacheServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageApacheService {

    /**
     * 查询商品
     *
     * @param commodityCode
     * @return
     */
    @LKAMethod(value = "getStorage 方法", description = "数据层订单接口实现类，查询商品接口")
    @LKAParam(name = "commodityCode", value = "商品编号")
    @Override
    public int getStorageTotal(String commodityCode) {
        return baseMapper.getStorageTotal(commodityCode);
    }

    /**
     * 扣除存储数量
     *
     * @param commodityCode
     * @param count
     */
    @LKAMethod(value = "deduct 方法", description = "数据层订单接口实现类，扣除存储数量，接口")
    @LKAParam(names = {"commodityCode", "count"}, values = {"商品编号", "数量"})
    @Override
    public int deduct(String commodityCode, int count) {
        return baseMapper.deduct(commodityCode, count);
    }
}

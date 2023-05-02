package cloud.liupeng.provider.storage.mapper;

import cloud.liupeng.domain.entity.storage.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

/**
 * MyBatis-plus 数据操作
 *
 * @author liupeng
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

    @Select("SELECT IFNULL(SUM(total), 0) AS total FROM tbl_storage WHERE commodity_code=#{commodityCode}")
    int getStorageTotal(@Param("commodityCode") String commodityCode);

    @Update("UPDATE tbl_storage SET total = total - ${count} WHERE commodity_code=#{commodityCode}")
    int deduct(@Param("commodityCode") String commodityCode, @Param("count") int count);
}

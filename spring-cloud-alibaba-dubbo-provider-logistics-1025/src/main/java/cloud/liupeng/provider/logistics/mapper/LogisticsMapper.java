package cloud.liupeng.provider.logistics.mapper;

import cloud.liupeng.domain.entity.logistics.Logistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

/**
 * MyBatis-plus 数据操作
 *
 * @author liupeng
 */
@Mapper
public interface LogisticsMapper extends BaseMapper<Logistics> {

    @Select("SELECT * FROM tbl_logistics WHERE id=#{id}")
    Logistics getLogisticsDatalayer(@Param("id") Integer id);
}

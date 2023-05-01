package cloud.liupeng.provider.order.mapper;

import cloud.liupeng.domain.entity.order.Order;
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
public interface OrderMapper extends BaseMapper<Order> {

    @Select("SELECT * FROM tbl_order WHERE id=#{orderId}")
    Order getOrder(@Param("orderId") Long orderId);

    @Update("UPDATE tbl_order SET status =#{status} WHERE id=#{orderId}")
    Order updateOrderStatus(@Param("orderId") Long orderId, @Param("status") Integer status);
}

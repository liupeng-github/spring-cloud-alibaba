package cloud.liupeng.provider.auth.mapper;

import cloud.liupeng.domain.entity.auth.Users;
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
public interface AuthMapper extends BaseMapper<Users> {

    @Select("SELECT * FROM tbl_user WHERE username=#{username}")
    Users loginAuth(@Param("username") String username);
}

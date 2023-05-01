package cloud.liupeng.provider.account.mapper;

import cloud.liupeng.domain.entity.account.Account;
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
public interface AccountMapper extends BaseMapper<Account> {

    @Select("SELECT * FROM tbl_account WHERE user_id=#{userId}")
    Account getByAccount(@Param("userId") String userId);

    @Update("UPDATE tbl_account SET total = total - ${money} WHERE user_id=#{userId}")
    int debit(@Param("userId") String userId, @Param("money") int money);
}

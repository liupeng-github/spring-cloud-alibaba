package cloud.liupeng.domain.entity.auth;

import cloud.liupeng.domain.base.BaseEntity;
import com.lk.api.annotation.LKAModel;
import com.lk.api.annotation.LKAProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 用户实体类
 *
 * @author liupeng
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
@LKAModel(value = "用户实体类", description = "JPA 自动生成表和字段")
public class Users extends BaseEntity {

    /**
     * 主键生成策略： 自增
     */
    @LKAProperty(value = "id", description = "账户 ID 主键，默认自动生成")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @LKAProperty(value = "username", description = "用户名")
    @Column(name = "username")
    private String username;

    /**
     * 用户密码
     */
    @LKAProperty(value = "password", description = "用户密码")
    @Column(name = "password")
    private String password;
}

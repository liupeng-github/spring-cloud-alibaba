package cloud.liupeng.domain.entity.account;

import cloud.liupeng.domain.base.BaseEntity;
import com.lk.api.annotation.LKAModel;
import com.lk.api.annotation.LKAProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 账户实体类
 *
 * @author liupeng
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_account")
@LKAModel(value = "账户实体类", description = "JPA 自动生成表和字段")
public class Account extends BaseEntity {

    /**
     * 主键生成策略： 自增
     */
    @LKAProperty(value = "id", description = "账户 ID 主键，默认自动生成")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户 ID
     */
    @LKAProperty(value = "userId", description = "用户 ID")
    @Column(name = "user_id")
    private String userId;

    /**
     * 总额度
     */
    @LKAProperty(value = "total", description = "总金额")
    @Column(name = "total")
    private Double total;

    /**
     * 已用额度
     */
    @LKAProperty(value = "used", description = "已用额度")
    @Column(name = "used")
    private Double used;

    /**
     * 剩余可用额度
     */
    @LKAProperty(value = "residue", description = "剩余可用额度")
    @Column(name = "residue")
    private Double residue;
}

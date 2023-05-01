package cloud.liupeng.domain.entity.pay;

import cloud.liupeng.domain.base.BaseEntity;
import com.lk.api.annotation.LKAModel;
import com.lk.api.annotation.LKAProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 支付实体类
 *
 * @author liupeng
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_pay")
@LKAModel(value = "支付实体类", description = "JPA 自动生成表和字段")
public class Pay extends BaseEntity {

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
     * 订单 ID
     */
    @LKAProperty(value = "order_id", description = "订单 ID")
    @Column(name = "order_id")
    private Long orderId;
}

package cloud.liupeng.domain.entity.order;

import cloud.liupeng.domain.base.BaseEntity;
import com.lk.api.annotation.LKAModel;
import com.lk.api.annotation.LKAProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 订单实体类
 *
 * @author liupeng
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_order")
@LKAModel(value = "订单实体类", description = "JPA 自动生成表和字段")
public class Order extends BaseEntity {

    /**
     * 主键生成策略： 自增
     */
    @LKAProperty(value = "id", description = "订单 ID 主键，默认使用雪花算法生成")
    @Id
    private Long id;

    /**
     * 用户 ID
     */
    @LKAProperty(value = "userId", description = "用户 ID")
    @Column(name = "user_id", length = 11)
    private String userId;

    /**
     * 商品 ID
     */
    @LKAProperty(value = "commodity_code", description = "商品 ID")
    @Column(name = "commodity_code", length = 11)
    private String commodityCode;

    /**
     * 数量
     */
    @LKAProperty(value = "count", description = "数量")
    @Column(name = "count", length = 11)
    private Integer count;

    /**
     * 金额
     */
    @LKAProperty(value = "money", description = "金额")
    @Column(name = "money", precision = 11, scale = 0)
    private Integer money;

    /**
     * 状态 0：订单创建中，1：创建成功
     */
    @LKAProperty(value = "status", description = "状态 0：订单创建中，1：创建成功")
    @Column(name = "status")
    private Integer status;
}

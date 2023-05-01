package cloud.liupeng.domain.entity.storage;

import cloud.liupeng.domain.base.BaseEntity;
import com.lk.api.annotation.LKAModel;
import com.lk.api.annotation.LKAProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 仓储实体类
 *
 * @author liupeng
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_storage")
@LKAModel(value = "仓储实体类", description = "JPA 自动生成表和字段")
public class Storage extends BaseEntity {

    /**
     * 主键生成策略： 自增
     */
    @LKAProperty(value = "id", description = "仓储 ID 主键，默认自动生成")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品 ID
     */
    @LKAProperty(value = "commodityCode", description = "商品 ID")
    @Column(name = "commodity_code")
    private Integer commodityCode;

    /**
     * 总库存
     */
    @LKAProperty(value = "total", description = "总库存")
    @Column(name = "total", length = 11)
    private int total;

    /**
     * 已用库存
     */
    @LKAProperty(value = "used", description = "已用库存")
    @Column(name = "used", length = 11)
    private int used;

    /**
     * 剩余库存
     */
    @LKAProperty(value = "residue", description = "剩余库存")
    @Column(name = "residue", length = 11)
    private int residue;
}

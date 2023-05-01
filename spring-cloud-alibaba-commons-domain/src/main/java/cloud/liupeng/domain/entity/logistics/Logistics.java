package cloud.liupeng.domain.entity.logistics;

import cloud.liupeng.domain.base.BaseEntity;
import com.lk.api.annotation.LKAModel;
import com.lk.api.annotation.LKAProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 物流实体类
 *
 * @author liupeng
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_logistics")
@LKAModel(value = "物流实体类", description = "JPA 自动生成表和字段")
public class Logistics extends BaseEntity {

    /**
     * 主键生成策略： 自增
     */
    @LKAProperty(value = "id", description = "账户 ID 主键，默认自动生成")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}

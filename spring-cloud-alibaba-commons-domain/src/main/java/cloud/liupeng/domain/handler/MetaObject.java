package cloud.liupeng.domain.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liupeng
 */
@Component
public class MetaObject implements MetaObjectHandler {

    /**
     * 使用MybatisPlus实现添加操作时，该方法会执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(org.apache.ibatis.reflection.MetaObject metaObject) {
        //参数：需要设置的属性；设置的时间；元数据(理解：表中的数据)
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    /**
     * 使用MybatisPlus实现修改操作时，该方法会执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(org.apache.ibatis.reflection.MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}

package cloud.liupeng.provider.account.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 配置 jpa 数据源
 * JPA是 Java Persistence API的简称，中文名Java持久层API，是JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。
 *
 * @author liupeng
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.account")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置实体管理器工厂
     *
     * @param jpaVendorAdapter
     * @return
     */
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        // 注入数据源
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        // 注入 JPA 适配器
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        // 注入 entity 所在包路径
        localContainerEntityManagerFactoryBean.setPackagesToScan("cloud.liupeng.domain.entity.account");
        return localContainerEntityManagerFactoryBean;
    }

    /**
     * 设置 jpa 厂商适配器
     *
     * @return
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        // 设置数据库类型（可使用 org.springframework.orm.jpa.vendor 包下的 Database 枚举类）
        hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
        // 设置打印 sql语句
        hibernateJpaVendorAdapter.setShowSql(true);
        // 设置不生成 ddl 语句
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        // 设置 hibernate 方言
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        return hibernateJpaVendorAdapter;
    }

    /**
     * 设置 jps 事务数据管理器
     *
     * @param entityManagerFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        // 设置实体类管理器工厂
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}

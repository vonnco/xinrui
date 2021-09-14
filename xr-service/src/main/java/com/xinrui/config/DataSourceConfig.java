package com.xinrui.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.xinrui.Enum.DataSourceEnum;
import com.xinrui.dataSource.ChooseDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 */
@Configuration
public class DataSourceConfig {

    /**
     * 主库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    @Primary
    public DataSource masterDB() {
    return DruidDataSourceBuilder.create().build();
    }
 
    /**
     * 从库
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slaveDB() {
        return DruidDataSourceBuilder.create().build();
    }
 
    /**
     * 主从动态配置
     */
    @Bean
    public ChooseDataSource dynamicDB() {
        ChooseDataSource chooseDataSource = new ChooseDataSource();
        chooseDataSource.setDefaultTargetDataSource(masterDB());
        Map<String, String> map = new HashMap<>();
        map.put(DataSourceEnum.MASTER.getValue(),"add,create,update,delete,remove,insert");
        map.put(DataSourceEnum.SLAVE.getValue(),"get,select,count,list,query,find");
        chooseDataSource.setMethodType(map);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.MASTER.getValue(), masterDB());
        targetDataSources.put(DataSourceEnum.SLAVE.getValue(), slaveDB());
        chooseDataSource.setTargetDataSources(targetDataSources);
        return chooseDataSource;
    }

    @Bean
    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDB") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath*:com/xinrui/dao/*Mapper.xml"));
        bean.setDataSource(dynamicDataSource);
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dynamicDB") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
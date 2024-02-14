package com.success.bigevent.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class SqlSessionConfig {
    //扫描的 xml 目录
    static final String MAPPER_LOCATION = "classpath:mybatis/disno1/mapper/*.xml";
    //自定义的mybatis config 文件位置
    static final String CONFIG_LOCATION = "classpath:mybatis/disno1/mybatis-config.xml";
    //扫描的 实体类 目录
//    static final String TYPE_ALIASES_PACKAGE = "org.fh.entity";

    /**
     * mybatis封装bean， 与数据库交互
     * @param masterDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)throws Exception {
        //创建
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        // 添加mapper 扫描路径
        //mapper用来实现dao流程的相关操作，将前台发送过来的对数据库的请求转化为数据库可以识别的sql语句，操作数据库并返回结果，将结果映射成程序能够识别的数据，将其赋值到相关的实体类里面
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        //设置mybatis configuration 扫描路径
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(CONFIG_LOCATION));
        // 设置typeAlias 包扫描路径
//        sessionFactory.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        return sessionFactory.getObject();
    }
}

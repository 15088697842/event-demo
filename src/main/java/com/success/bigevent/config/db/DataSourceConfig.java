package com.success.bigevent.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    //使用@value读取application.properties的内容
    @Value("${datasource.no1.url}")
    private String url;

    @Value("${datasource.no1.username}")
    private String user;

    @Value("${datasource.no1.password}")
    private String password;

    @Value("${datasource.no1.driver-class-name}")
    private String driverClass;
    //该bean返回的是数据库的连接池
    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //以下参数在application.properties文件中定义
        //  驱动类充当了 Java 应用程序与特定数据库之间的桥梁，使得应用程序能够使用 JDBC API 与数据库进行通信
        // 其实就是JDBC API的一个实现，一个具体类，  建立连接等
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }
}

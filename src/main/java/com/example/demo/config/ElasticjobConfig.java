package com.example.demo.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by qukoucai001 on 2019/3/7.
 */
@Data
@Configuration
public class ElasticjobConfig {

    @Value("${robinQuartz.app.zkAddress}")
    private String zkNodes;

    @Value("${robinQuartz.app.namespace}")
    private String namespace;

    @Bean
    public ZookeeperConfiguration zkConfig() {
        return new ZookeeperConfiguration(zkNodes, namespace);
    }

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(ZookeeperConfiguration config) {
        return new ZookeeperRegistryCenter(config);
    }
}

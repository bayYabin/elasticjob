package com.example.demo.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by qukoucai001 on 2019/3/7.
 */
@Configuration
public class ElasticJobs {
    @Autowired
    private ZookeeperRegistryCenter regCenter;

    @Bean(initMethod = "init")
    public JobScheduler settlementJobScheduler(@Autowired SimpleJob simpleJob,
                                               @Value("${simpleJob.billCronExpress}") final String cron,
                                               @Value("${simpleJob.shardingCount}") int shardingCount,
                                               @Value("${simpleJob.shardingItemParameters}") String shardingItemParameters) {
        return new SpringJobScheduler(simpleJob,
                regCenter,
                getLiteJobConfiguration(simpleJob.getClass(),
                        cron,
                        shardingCount,
                        shardingItemParameters));
    }

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(),
                cron,
                shardingTotalCount).shardingItemParameters(shardingItemParameters).build(),
                jobClass.getCanonicalName())).overwrite(true).build();
    }
}

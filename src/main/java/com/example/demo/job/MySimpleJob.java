package com.example.demo.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * Created by qukoucai001 on 2019/3/7.
 */
@Component("SimpleJob")
public class MySimpleJob implements SimpleJob {


    /**
     * job 执行实例
     * @param shardingContext
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        int shardIndx = shardingContext.getShardingItem();
        if (shardIndx == 0) {
            System.out.println("the first one");
        } else if(shardIndx == 1){
            System.out.println("the second one");
        }else{
            System.out.println("the third one");
        }
    }
}

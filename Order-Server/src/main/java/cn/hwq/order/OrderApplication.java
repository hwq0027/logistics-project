package cn.hwq.order;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }

    @Bean
    public RedissonClient redissonClient(){
        // 配置类
        Config config = new Config();
        //添加redis地址,这里添加的是单点的地址,也可以使用config.useClusterServers()添加集群地址
        config.useSingleServer().setAddress("redis://101.34.103.51:6379")
                .setPassword("hwq666");
        // 创建Redisson客户端
        return Redisson.create(config);
    }
}

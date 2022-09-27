package cn.hwq.order.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    /**声明交换机，声明名称，持久化，当所有消费者断开连接后是否自动删除队列*/
    @Bean
    public TopicExchange oderExchange(){
        return  new TopicExchange("order.Exchange",true,false);
    }
    /**
    *声明其他交换机案例
    *new FanoutExchange("交换机名称");
    */


    // 声明普通的 simple.queue队列，并且为其指定死信交换机：dl.direct
    @Bean
    public Queue orderQueue(){
        return QueueBuilder.durable("order.queue") // 指定队列名称，并持久化
                .deadLetterExchange("dl.Exchange") // 指定死信交换机
                .deadLetterRoutingKey("simple")
                .build();
    }

    // 订单队列 与 订单交换机绑定
    @Bean
    public Binding orderBinding(){
        return BindingBuilder.bind(orderQueue()).to(oderExchange()).with("ls.order");
    }


    // 声明死信交换机 dl.direct
    @Bean
    public DirectExchange dlExchange(){
        return new DirectExchange("dl.Exchange", true, false);
    }

    // 声明存储死信的队列 dl.queue
    @Bean
    public Queue dlQueue(){
        return new Queue("dl.queue", true);
    }



    // 将死信队列 与 死信交换机绑定
    @Bean
    public Binding dlBinding(){
        return BindingBuilder.bind(dlQueue()).to(dlExchange()).with("simple");
    }

}
//package cn.hwq.gateway;
//
//import cn.hutool.core.bean.BeanUtil;
//import cn.hutool.core.util.StrUtil;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 自定义一个全局过滤器
// *      实现 globalfilter , ordered接口
// */
//@Component
//public class LoginFilter implements GlobalFilter,Ordered {
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    /**
//     * 执行过滤器中的业务逻辑
//     *     对请求参数中的access-token进行判断
//     *      如果存在此参数:代表已经认证成功
//     *      如果不存在此参数 : 认证失败.
//     *  ServerWebExchange : 相当于请求和响应的上下文(zuul中的RequestContext)
//     */
//
//    /**
//     *  处理当前请求，有必要的话通过{@link GatewayFilterChain}将请求交给下一个过滤器处理
//     *
//     * @param exchange 请求上下文，里面可以获取Request、Response等信息
//     * @param chain 用来把请求委托给下一个过滤器
//     * @return {@code Mono<Void>} 返回标示当前过滤器业务结束
//     */
//
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        System.out.println("执行了自定义的全局过滤器");
//        // 1.获取请求头中的token
//        List<String> authorization = exchange.getRequest().getHeaders().get("authorization");
//        if (authorization.size()==0){
//            System.out.println("令牌为空1");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete(); //请求结束
//        }
//        String token = authorization.get(0);
//        if (StrUtil.isBlank(token)) {
//            System.out.println("令牌为空2");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete(); //请求结束
//        }
////        1.获取请求头中的account
//        List<String> accountList = exchange.getRequest().getHeaders().get("account");
//        if (accountList.size()==0){
//            System.out.println("账号为空1");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete(); //请求结束
//        }
//        String account = accountList.get(0);
//        if (StrUtil.isBlank(token)) {
//           System.out.println("账号为空2");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete(); //请求结束
//        }
//
//        String key  = "LOGIN_USER_KEY_" + account;
//        String value = stringRedisTemplate.opsForValue().get(key);
//        // 3.判断用户是否存在
//        if (StringUtils.isBlank(value)) {
//            System.out.println("用户未登陆");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete(); //请求结束
//        }
//
//        if (!value.equals(token)){
//            System.out.println("令牌错误");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete(); //请求结束
//        }
//        //4.如果通过,继续执行
//        System.out.println("通过了");
//        return chain.filter(exchange); //继续向下执行
//    }
//
//    /**
//     * 指定过滤器的执行顺序 , 返回值越小,执行优先级越高,也可以不实现Ordered接口,直接类上加@Order(0)注解
//     */
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
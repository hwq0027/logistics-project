package cn.hwq.order.filter;

import cn.hwq.jwt.JwtUtil;
import cn.hwq.utils.UserHolder;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.判断是否需要拦截
        String token = request.getHeader("token");


        if (StringUtils.isBlank(token)) {
            // 没有，需要拦截，设置状态码
            response.setStatus(403);
            // 拦截
            return false;
        }
        /**
         * 此处可优化,需要头信息中携带账号和令牌,然后验证令牌是否正确
         * */
        // 有用户，则放行
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            response.setStatus(403);
            // 拦截
            return false;
        }
        String userId = claims.getSubject();
        UserHolder.saveObject(userId);
        return true;
    }
}

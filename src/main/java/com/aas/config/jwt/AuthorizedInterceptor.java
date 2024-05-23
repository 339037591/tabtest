package com.aas.config.jwt;

import com.aas.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthorizedInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
            String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token

            // 如果不是映射到方法直接通过
            if(!(object instanceof HandlerMethod)){
                return true;
            }
            HandlerMethod handlerMethod=(HandlerMethod)object;
            Method method=handlerMethod.getMethod();
            //先验证是否标记不需要验证登陆验证
            //类注解
            NotLogined classnotLogined = method.getDeclaringClass().getAnnotation(NotLogined.class);
            //方法注解
            NotLogined methodnotLogined = method.getAnnotation(NotLogined.class);
            if ((classnotLogined != null || methodnotLogined != null) ) {
                httpServletRequest.setAttribute("token",token);
                return true;
            }else{//全局验证token
                if (StringUtils.isEmpty(token)) {
                    throw new RuntimeException("403");
                }
                boolean verity = JwtUtils.verity(token);
                if (!verity) {
                    throw new RuntimeException("403");
                }
                return true;
            }

        }

        @Override
        public void postHandle(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse,
                               Object o, ModelAndView modelAndView) throws Exception {

        }
        @Override
        public void afterCompletion(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    Object o, Exception e) throws Exception {
        }
}

package com.aas.controller;

import com.aas.config.WeChatConfig;
import com.aas.util.HttpUtils;
import com.aas.util.JwtUtils;
import java.net.URLEncoder;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private WeChatConfig wechatConfig;

    @RequestMapping("/wechatLogin")
    public void weChatLogin()throws Exception{
        request.setCharacterEncoding("UTF-8");
        String code = request.getParameter("code");
        //第一次登陆，需回调
        if(StringUtils.isNotBlank(code)){
            //获取token
            logger.info("postTokenURL:"+wechatConfig.getTokenURL());
            JSONObject jsonToken = JSONObject.parseObject(HttpUtils.builder().url(wechatConfig.getTokenURL()).post(false).sync());
            String access_token = jsonToken.getString("access_token");
            logger.info("access_token:"+access_token);

            logger.info("postuserURL:"+wechatConfig.getUserURL());
            String userURL = wechatConfig.getUserURL().replace("{ACCESS_TOKEN}",access_token).replace("{CODE}",code);
            logger.info("userURL:"+userURL);
            JSONObject jsonUser = JSONObject.parseObject(HttpUtils.builder().url(userURL).post(false).sync());

            String userId = jsonUser.getString("UserId");
            logger.info("userId:"+userId);
            //下面可做登录验证,保存日志等
            authSuccess(userId);
        }else{
            //重定向到当前方法
            response.sendRedirect(wechatConfig.getRegetURL());
        }
    }

    /**
     * 验证用户ID在系统中是否存在
     * @param userId 微信获取到的用户ID
     */
    public void authSuccess(String userId) throws Exception {
        //1、先验证用户ID是否在数据库中存在
        /**
         * 验证用户ID是否存在代码块
         */
        //2、生成token,并把必要参数放到Cookie中前端从Cookie中获取，需要转成UTF-8不然前端获取不到中文信息
        //也可以把参数放到首页URL?之后，具体与前端协调
        String tokenString = JwtUtils.sign(userId,userId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", URLEncoder.encode(tokenString, "utf-8"));

        for (Map.Entry<String, String> aa : map.entrySet()){
            Cookie tokenCookie = new Cookie(aa.getKey(), aa.getValue());
            tokenCookie.setPath("/");
            tokenCookie.setMaxAge(60 * 60);
            response.addCookie(tokenCookie);
        }
        logger.info("tokenString" + URLEncoder.encode(tokenString, "utf-8"));
        logger.info("tokenString" + tokenString);

        //3、重定向到系统首页
        response.sendRedirect(wechatConfig.getIndexURL());
    }

}

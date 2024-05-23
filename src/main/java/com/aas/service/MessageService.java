package com.aas.service;

import com.aas.config.WeChatConfig;
import com.aas.controller.base.Result;
import com.aas.util.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {

    private static Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private WeChatConfig wechatConfig;

    /**
     *  推送普通文本消息
     * @param tousers 接收消息人账号 格式:user1,user2
     * @param content 消息内容
     * @return
     */
    public Result sendMassage(String tousers,String content){
        try{
            String wechatToken = getWechatToken();
            String messageurl = wechatConfig.getMessageURL().replace("{ACCESS_TOKEN}",wechatToken);
            tousers = tousers.replaceAll(",","|");
            JSONObject params = new JSONObject();
            params.put("touser", tousers);
            params.put("agentid", wechatConfig.getAppId());
            JSONObject text = new JSONObject();
            text.put("content",content);
            params.put("msgtype", "text");
            params.put("text",text);
            JSONObject jsonResult = JSONObject.parseObject(HttpUtils.builder()
                    .url(messageurl).post(params.toJSONString()).sync());
            System.out.println("-----------------"+params);
            logger.info("send-message-result:"+jsonResult);
            if(!"0".equals(jsonResult.getString("errcode"))){
                return Result.errro(Integer.valueOf(jsonResult.getString("errcode")),jsonResult.getString("errmsg"));
            }
            return Result.success();
        }catch (Exception e){
            logger.error("failed-error", e.fillInStackTrace());
            return Result.errro(500,"消息推送失败！");
        }
    }

    /**
     * 推送图片消息
     * @param tousers 接收消息人账号 格式:user1,user2
     * @param path 图片地址
     * @return
     */
    public Result sendImgMessage(String tousers,String path){
        try{
            String wechatToken = getWechatToken();
            tousers = tousers.replaceAll(",","|");
            //上传临时素材
            String uploadURL = wechatConfig.getUploadURL().replace("{ACCESS_TOKEN}",wechatToken).replace("{TYPE}","image");
            JSONObject jsonUploadImg = JSONObject.parseObject(HttpUtils.builder().url(uploadURL).upLoadImage(path).sync());
            logger.info("send-upload-img-result:"+jsonUploadImg);
            String mediaId = jsonUploadImg.getString("media_id");
            JSONObject params = new JSONObject();
            params.put("touser",tousers);
            params.put("msgtype","image");
            params.put("agentid",wechatConfig.getAppId());
            JSONObject image = new JSONObject();
            image.put("media_id",mediaId);
            params.put("image",image);
            String messageurl = wechatConfig.getMessageURL().replace("{ACCESS_TOKEN}",wechatToken);
            JSONObject jsonResult = JSONObject.parseObject(HttpUtils.builder()
                    .url(messageurl).post(params.toJSONString()).sync());
            logger.info("send-message-result:"+jsonResult);
            if(!"0".equals(jsonResult.getString("errcode"))){
                return Result.errro(Integer.valueOf(jsonResult.getString("errcode")),jsonResult.getString("errmsg"));
            }
            return Result.success();
        }catch (Exception e){
            logger.error("failed-error", e.fillInStackTrace());
            return Result.errro(500,"消息推送失败！");
        }
    }

    /**
     * 获取WeChat token
     * @return
     */
    public String getWechatToken(){
        return JSONObject.parseObject(HttpUtils.builder().url(wechatConfig.getTokenURL()).post(false).sync()).getString("access_token");
    }
}

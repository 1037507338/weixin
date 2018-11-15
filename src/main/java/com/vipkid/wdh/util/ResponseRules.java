package com.vipkid.wdh.util;

import com.vipkid.wdh.vo.WeixinMessage;

import java.util.Date;

public class ResponseRules {

    public static final String M_TEXT = "text";
    public static final String M_IMAGE = "image";
    public static final String M_VOICE = "voice";
    public static final String M_LINK = "link";
    public static final String M_LOCATION = "location";
    public static final String M_EVENT = "event";
    public static final String M_SUBSCRIBE = "subscribe";
    public static final String M_UNSUBSCRIBE = "unsubscribe";
    public static final String M_CLICK = "CLICK";
    public static final String M_VIEW = "VIEW";

    private static WeixinMessage baseMessage(String FromUserName, String ToUserName){
        WeixinMessage message = new WeixinMessage();
        message.setToUserName(FromUserName);
        message.setFromUserName(ToUserName);
        message.setCreateTime(new Date().getTime());
        return message;
    }

    public static String subMessage(String FromUserName, String ToUserName) {
        WeixinMessage message = baseMessage(FromUserName, ToUserName);
        message.setContent("感谢您的关注~");
        message.setMsgType(M_TEXT);
        return  MessageUtil.messageToxml(message);
    }

    public static String commonMessage(String FromUserName, String ToUserName) {
        WeixinMessage message = baseMessage(FromUserName, ToUserName);
        message.setContent("该资源我们暂未收录，您的需求我们已经记录，并尽快更新资源库");
        message.setMsgType(M_TEXT);
        return  MessageUtil.messageToxml(message);
    }

    public static String javaMessage(String FromUserName, String ToUserName) {
        WeixinMessage message = baseMessage(FromUserName, ToUserName);
        message.setContent("项目实战：\n" +
                "\n"+
                "SSM到Spring Boot-从零开发校园商铺平台\n" +
                "链接：https://pan.baidu.com/s/1_J7GfHUFnErTgH9byjirug 提取码：mbtj\n" +
                "\n" +
                "Spring Boot企业微信点餐系统\n" +
                "链接：https://pan.baidu.com/s/1a0uGJjtoWRu1kxDumN1p_A 提取码：zmw6 ");
        message.setMsgType("text");
        return  MessageUtil.messageToxml(message);
    }

}

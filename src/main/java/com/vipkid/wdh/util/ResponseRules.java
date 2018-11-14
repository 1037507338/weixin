package com.vipkid.wdh.util;

import com.vipkid.wdh.vo.WeixinMessage;

import java.util.Date;

public class ResponseRules {

    private static WeixinMessage baseMessage(String FromUserName, String ToUserName){
        WeixinMessage message = new WeixinMessage();
        message.setToUserName(FromUserName);
        message.setFromUserName(ToUserName);
        message.setCreateTime(new Date().getTime());
        return message;
    }

    public static String commonMessage(String FromUserName, String ToUserName) {
        WeixinMessage message = baseMessage(FromUserName, ToUserName);
        message.setContent("该资源我们暂未收录，您的需求我们已经记录，并尽快更新资源库");
        message.setMsgType("text");
        return  MessageUtil.messageToxml(message);
    }

    public static String javaMessage(String FromUserName, String ToUserName) {
        WeixinMessage message = baseMessage(FromUserName, ToUserName);
        message.setContent("该资源我们暂未收录，您的需求我们已经记录，并会尽快更新资源库");
        message.setMsgType("text");
        return  MessageUtil.messageToxml(message);
    }

}

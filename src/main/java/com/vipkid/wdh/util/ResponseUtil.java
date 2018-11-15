package com.vipkid.wdh.util;

import static com.vipkid.wdh.util.ResponseRules.*;

public class ResponseUtil {

    public static String doResponse(String MsgType, String Content, String ToUserName, String FromUserName){
        String message = "";
        switch (MsgType){
            case M_IMAGE:
                return message;
            case M_VOICE:
                return message;
            default:
                if(Content.equals("实战")){
                    return ResponseRules.javaMessage(FromUserName, ToUserName);
                }else {
                    return ResponseRules.commonMessage(FromUserName, ToUserName);
                }

        }
    }

    public static String doEventResponse(String eventType, String Content, String ToUserName, String FromUserName){
        String message = "";
        switch (eventType){
            case M_SUBSCRIBE:
                return ResponseRules.subMessage(FromUserName, ToUserName);
            case M_UNSUBSCRIBE:
                return message;
            default:
                if(Content.equals("实战")){
                    return ResponseRules.javaMessage(FromUserName, ToUserName);
                }else {
                    return ResponseRules.commonMessage(FromUserName, ToUserName);
                }

        }
    }

}

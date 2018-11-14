package com.vipkid.wdh.vo;

import lombok.Data;

@Data
public class WeixinMessage {

    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;

}

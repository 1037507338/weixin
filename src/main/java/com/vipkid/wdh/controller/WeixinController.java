package com.vipkid.wdh.controller;

import com.vipkid.wdh.util.MessageUtil;
import com.vipkid.wdh.util.ResponseRules;
import com.vipkid.wdh.util.ResponseUtil;
import com.vipkid.wdh.util.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import static com.vipkid.wdh.util.ResponseRules.M_EVENT;
import static com.vipkid.wdh.util.ResponseRules.M_SUBSCRIBE;
import static com.vipkid.wdh.util.ResponseRules.M_TEXT;

@Slf4j
@Controller
@RequestMapping("/weixin/auth")
public class WeixinController {

    private static final String TOKEN = "wangdonghao";

    @GetMapping()
    public String auth(String signature, String timestamp, String nonce, String echostr){
        String[] strs = {TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        StringBuffer sb = new StringBuffer();
        for(String str:strs)sb.append(str);
        String code = SHA1.encode(sb.toString());
        log.debug("密文："+code);
        log.debug("目标："+signature);
        if(code.equals(signature)){
            return echostr;
        }
        log.debug("错误");
        return "错误";
    }

    @PostMapping()
    public void commmunicate(HttpServletRequest req, HttpServletResponse res){
        res.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String,String> map = MessageUtil.xmlToMap(req);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");
        log.debug("用户输入为：" + Content);
        //处理文本类型,回复用户输入的内容
        String message = "";
        if(MsgType.equals(M_EVENT)){
            String eventType = map.get("Event");
            message = ResponseUtil.doEventResponse(eventType, Content, ToUserName, FromUserName);
        }else {
            message = ResponseUtil.doResponse(MsgType, Content, ToUserName, FromUserName);
        }

        try {
            out = res.getWriter();
            out.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

}

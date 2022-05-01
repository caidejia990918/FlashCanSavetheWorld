package com.flash.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.common.lang.Result;
import com.flash.config.WxMaConfiguration;

import com.flash.entry.WxUser;
import com.flash.service.UserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;
    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public Result login(@PathVariable String appid, String code ) {
        if (StringUtils.isBlank(code)) {
            return Result.fail("empty jscode");
        }

        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
          WxUser wxUser = userService.getOne(new QueryWrapper<WxUser>().eq("openid", session.getOpenid()));
         if(wxUser==null){
           WxUser wxUser1 = new WxUser();
           wxUser1.setOpenid(session.getOpenid());
           wxUser1.setAvatar("../../static/img/index/notlogin.jpg");
           userService.saveOrUpdate(wxUser1);
           return Result.succ(200,"登录成功",wxUser1);
         }
          return Result.succ(200,"登录成功",wxUser);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return Result.fail(e.toString());
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @PostMapping("/postAuth")
    public Result AuthUserinfo(@RequestBody WxUser wxUser){
//      System.out.println(wxUser);
      if(userService.saveOrUpdate(wxUser,new QueryWrapper<WxUser>().eq("openid",wxUser.getOpenid()))){
        return Result.succ(200,"授权成功",wxUser);
      }
      else{
        return Result.fail("授权失败");
      }



    }

    @GetMapping("/getData")
    public Result getData(){
      List<WxUser> list = userService.list();
      return Result.succ(list);
    }

    @PostMapping("/changeAuth")
    public Result changeAuth(@RequestBody Map<String,String> params){
      int auth =  Integer.parseInt( params.get("auth"));
      String openid =params.get("openid");
      WxUser wxUser = userService.getOne(new QueryWrapper<WxUser>().eq("openid", openid));
      wxUser.setRule(auth);
      userService.saveOrUpdate(wxUser);
      return Result.succ("操作成功");
    }

}

package com.flash.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.common.lang.Result;
import com.flash.entry.WaterInfo;
import com.flash.service.WaterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/{openid}")
public class UserController {


  @Autowired
  WaterInfoService waterInfoService;

  @PostMapping("/declare")
  public Result Declare(@PathVariable String openid, @RequestBody WaterInfo waterinfo) {

    try {
      waterinfo.setOpenid(openid);
      waterinfo.setCreated(LocalDateTime.now());
      String s = NeedReport(waterinfo.getWaterJudge());
      waterinfo.setNeedUploadReason(s);
      if(s.equals("是")){
        waterinfo.setStatus("未处理");
      }else{
        waterinfo.setStatus("已通过审核");
      }
      waterInfoService.save(waterinfo);
      return Result.succ(200, "上传成功", waterinfo);
    } catch (Exception e) {
      System.err.println(e);
      return Result.fail(e.toString());
    }


  }

  @GetMapping("/getWaterRes")
  public Result GetWaterRes(@PathVariable String openid){
    System.out.println(openid);
    List<WaterInfo> waterInfos = waterInfoService.list(new QueryWrapper<WaterInfo>().eq("openid", openid).orderByDesc("created"));
    System.out.println(waterInfos);
    return Result.succ(waterInfos);
  }


  public String NeedReport(String msg) {
    String arr[] = {"有大量的铁、锰和被氧化的铁细菌，重度污染", "正常", "有少量的铁、锰和被氧化的铁细菌，轻度污染", "含铁量已超标", "水中藻类及水生物较大量，氮、磷等营养物质，水体富营养化"};
    int i = 0;
    System.out.println(msg);
    for (; i < arr.length; i++) {
      if (msg.equals(arr[i])) {
        break;
      }
    }
    System.out.println(i);
    if (i == 1 || i == 2) {
      return "否";
    } else {
      return "是";
    }
  }


}

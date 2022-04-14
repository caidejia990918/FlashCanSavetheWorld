package com.flash.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.common.lang.Result;
import com.flash.entry.InfoReportVO;
import com.flash.entry.WaterInfo;
import com.flash.entry.WaterReport;
import com.flash.entry.infoByCompanyVO;
import com.flash.service.WaterInfoService;
import com.flash.service.WaterReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/{openid}")
public class UserController {


  @Autowired
  WaterInfoService waterInfoService;
  @Autowired
  WaterReportService waterReportService;

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
    List<WaterInfo> waterInfos = waterInfoService.list(new QueryWrapper<WaterInfo>().eq("openid", openid).orderByDesc("created"));
    return Result.succ(waterInfos);
  }

  @GetMapping("/getWaterToRp")
  public Result GetWaterToRp(@PathVariable String openid){
    List<WaterInfo> waterInfos = waterInfoService.list(new QueryWrapper<WaterInfo>().eq("openid", openid).eq("need_upload_reason","是").orderByDesc("created"));
    return Result.succ(waterInfos);
  }

  @GetMapping("/getWaterRp")
  public Result GetWaterRp(@PathVariable String openid){
    List<WaterReport> waterInfos = waterReportService.list(new QueryWrapper<WaterReport>().eq("openid", openid).orderByDesc("created"));
    return Result.succ(waterInfos);
  }


  @PostMapping("/uploadReason")
  public Result UploadReason(@PathVariable String openid, @RequestBody WaterReport waterReport){
    waterReport.setOpenid(openid);
    waterReport.setCreated(LocalDateTime.now());
    waterReport.setRecord("等待负责人审核");
    try {
      WaterInfo waterInfo = new WaterInfo();
      waterInfo.setStatus("审核中");
      waterInfo.setNeedUploadReason("否");
      waterInfoService.update(waterInfo,new QueryWrapper<WaterInfo>().eq("id", waterReport.getInfoId()));
      waterReportService.save(waterReport);
      return Result.succ(200,"提交成功",waterReport);
    }catch (Exception e){
      System.out.println(e);
      return Result.fail(e.toString());
    }


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

package com.flash.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flash.common.lang.Result;
import com.flash.entry.*;
import com.flash.service.WaterInfoService;
import com.flash.service.WaterReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{openid}")
public class AdminController {
  @Autowired
  WaterInfoService waterInfoService;
  @Autowired
  WaterReportService waterReportService;

  @GetMapping("/adminGetInfo")
  public Result adminGetInfo(){
    List<InfoReportVO> all = waterInfoService.getALL();
    return Result.succ(all);
  }

  @GetMapping("/adminGetInfoForAll")
  public Result adminGetInfoForAll(){
    List<infoByCompanyVO> submitAll = waterInfoService.getSubmitAll();
    return Result.succ(submitAll);
  }

  @PostMapping("/admin/deal")
  public Result dealReport(@RequestBody Check check ,@PathVariable String openid){
    if(check.getStatus().equals("通过")) {
      WaterInfo waterInfo = new WaterInfo();
      waterInfo.setStatus("已通过审核");
      waterInfoService.update(waterInfo,new QueryWrapper<WaterInfo>().eq("id", check.getId()));
      WaterReport waterReport = new WaterReport();
      waterReport.setRecord(check.getRecord());
     waterReportService.update(waterReport,new QueryWrapper<WaterReport>().eq("id",check.getId2()));
    }else{
      WaterInfo waterInfo = new WaterInfo();
      waterInfo.setStatus("审核不通过");
      waterInfo.setNeedUploadReason("是");
      waterInfoService.update(waterInfo,new QueryWrapper<WaterInfo>().eq("id", check.getId()));
      WaterReport waterReport = new WaterReport();
      waterReport.setRecord(check.getRecord());
      waterReportService.update(waterReport,new QueryWrapper<WaterReport>().eq("id",check.getId2()));
    }
    return Result.succ(check);
  }

}

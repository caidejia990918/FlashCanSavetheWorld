package com.flash.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flash.entry.InfoReportVO;
import com.flash.entry.WaterInfo;
import com.flash.entry.WxUser;
import com.flash.entry.infoByCompanyVO;
import com.flash.mapper.WaterInfoMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-05-25
 */
public interface WaterInfoService extends IService<WaterInfo> {

  List<InfoReportVO> getALL();
  List<infoByCompanyVO>getSubmitAll();

}

package com.flash.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flash.entry.InfoReportVO;
import com.flash.entry.WaterInfo;
import com.flash.entry.WxUser;
import com.flash.entry.infoByCompanyVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;



public interface WaterInfoMapper extends BaseMapper<WaterInfo> {

  @Select("SELECT nickname,water_info.company,water_info.description,water_info.water_judge as first_judge,water_report.water_judge as second_judge,location_address,solution,reason,water_info.created as created_f,water_report.created as created_s from users,water_info,water_report WHERE water_report.info_id = water_info.id and users.openid = water_report.openid and water_info.status='审核中'")
  List<InfoReportVO> getALL();

  @Select("SELECT nickname,water_info.company,status,(IFNULL(record,'无提交记录')) as record ,water_info.description,water_info.water_judge as first_judge, (IFNULL(water_report.water_judge,'无提交记录')) as  second_judge,location_address,(IFNULL(solution,'无提交记录')) as solution,(IFNULL(reason,'无提交记录')) as reason,water_info.created as created_f,water_report.created as created_s from water_info LEFT JOIN water_report ON water_report.info_id = water_info.id LEFT JOIN users on users.openid = water_info.openid")
  List<infoByCompanyVO>getSubmitAll();

}

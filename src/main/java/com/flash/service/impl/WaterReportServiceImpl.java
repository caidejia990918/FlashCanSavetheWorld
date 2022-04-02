package com.flash.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flash.entry.WaterInfo;
import com.flash.entry.WaterReport;
import com.flash.mapper.WaterInfoMapper;
import com.flash.mapper.WaterReportMapper;
import com.flash.service.WaterInfoService;
import com.flash.service.WaterReportService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-05-25
 */
@Service
public class WaterReportServiceImpl extends ServiceImpl<WaterReportMapper, WaterReport> implements WaterReportService {

}

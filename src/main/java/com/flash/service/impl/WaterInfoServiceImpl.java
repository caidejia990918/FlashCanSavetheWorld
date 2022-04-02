package com.flash.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flash.entry.WaterInfo;
import com.flash.entry.WxUser;
import com.flash.mapper.UserMapper;
import com.flash.mapper.WaterInfoMapper;
import com.flash.service.UserService;
import com.flash.service.WaterInfoService;
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
public class WaterInfoServiceImpl extends ServiceImpl<WaterInfoMapper, WaterInfo> implements WaterInfoService {

}

package com.flash.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("users")
public class WxUser implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "uuid", type = IdType.AUTO)
  private long uuid;

  private String openid;

  private int rule;

  private String password;

  private String avatar;

  private String sex;

  private String nickname;


}

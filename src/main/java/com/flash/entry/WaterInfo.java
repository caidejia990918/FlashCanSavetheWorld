package com.flash.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("water_info")
@Data
public class WaterInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private long id;

  private String openid;

  @JsonProperty("waterJudge")
  private String waterJudge;

  private String description;

  @JsonProperty("locationAddress")
  private String locationAddress;

  @JsonFormat(pattern="yyyy-M-dd HH:mm")
  private LocalDateTime created;

  @JsonProperty("needUploadReason")
  private String needUploadReason;

  private String company;

  private String status;

}

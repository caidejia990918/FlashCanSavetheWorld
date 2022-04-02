package com.flash.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("water_report")
@Data
public class WaterReport implements Serializable {
  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private long id;

  @JsonProperty("infoId")
  private long infoId;

  private String openid;

  @JsonProperty("waterJudge")
  private String waterJudge;

  private String description;

  private String reason;

  private String solution;

  @JsonFormat(pattern="yyyy-M-dd HH:mm")
  private LocalDateTime created;

  private String company;

  private String record;


}

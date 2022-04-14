package com.flash.entry;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class InfoReportVO implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private Long id2;
  private String nickname;
  @JsonProperty("firstJudge")
  private String firstJudge;

  @JsonProperty("secondJudge")
  private String secondJudge;

  private String description;

  @JsonProperty("locationAddress")
  private String locationAddress;

  private String reason;

  private String solution;

  @JsonFormat(pattern="yyyy-M-dd HH:mm")
  private LocalDateTime createdF;

  @JsonFormat(pattern="yyyy-M-dd HH:mm")
  private LocalDateTime createdS;

  private String company;
}

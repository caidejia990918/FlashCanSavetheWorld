package com.flash.entry;

import lombok.Data;

import java.io.Serializable;

@Data
public class Check implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Long id2;
  private String status;
  private String record;
}

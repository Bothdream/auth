package com.zte.auth.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
   private Long id;
   private String productName;
   private Date createTime;
}

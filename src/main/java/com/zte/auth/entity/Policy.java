package com.zte.auth.entity;

import lombok.Data;
import java.util.Set;

@Data
public class Policy {
   private String policId;
   private String mark;
   private Set<String> power;
}

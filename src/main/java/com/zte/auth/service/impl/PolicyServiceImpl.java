package com.zte.auth.service.impl;

import com.zte.auth.entity.Policy;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class PolicyServiceImpl {
   private Map<String, Policy> policyMap;
   public PolicyServiceImpl(List<Policy> policies){
       policyMap = policies.stream().collect(toMap(Policy::getPolicId,k->k,(cur,old)->cur));
   }
   public Policy getPolicyById(String policyId){
      return policyMap.get(policyId);
   }
}

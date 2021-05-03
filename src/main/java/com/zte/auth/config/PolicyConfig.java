package com.zte.auth.config;

import cn.hutool.core.io.resource.ResourceUtil;
import com.alibaba.fastjson.JSON;
import com.zte.auth.entity.Policy;
import com.zte.auth.service.impl.PolicyServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PolicyConfig {
    @Bean("policyService")
    public PolicyServiceImpl initPolicyConfig() throws Exception {
        //FileReader fileReader = new FileReader("classpath:policy.json"); 有bug，找不到资源文件
        String result = ResourceUtil.readUtf8Str("policy.json");
        if (StringUtils.isBlank(result)) {
            throw new Exception("策略文件沒有配置");
        }
        List<Policy> policies = JSON.parseArray(result,Policy.class);
        return new PolicyServiceImpl(policies);
    }
}

package com.zte.auth.config;

import cn.hutool.core.io.file.FileReader;
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
    public PolicyServiceImpl f() throws Exception {
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader("classpath:policy.json");
        String result = fileReader.readString();
        if (StringUtils.isBlank(result)) {
            throw new Exception("策略文件沒有配置");
        }
        List<Policy> policies = JSON.parseArray(result,Policy.class);
        return new PolicyServiceImpl(policies);
    }
}

package com.zte.auth.controller;

import com.zte.auth.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * controller层测试
 */
@Slf4j
@SpringBootTest
public class UserControllerTest {

    private static MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void test() throws Exception {

        String strjson = "{\"bo\":{\"account\":\"zhangsan\",\"enabledFlag\":\"1\"},\"code\":{\"msgId\":\"SUCESS\",\"code\":\"000\",\"msg\":\"操作成功\"},\"other\":{}}";
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .request(HttpMethod.GET,"/api/users")
                        .contentType("application/json")
                        .content(strjson)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bo.account").value("zhangsan"))
                .andDo(print()).andReturn();
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void mockGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users")
                        .param("id","123")
                        .param("name","san")
                        .sessionAttr("name","123")
                        .cookie(new Cookie("key","value"))
                        .contentType("application/x-www-form-urlencoded")
                        .accept("application/json")
                        .header("key","val"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print()).andReturn();
    }

    @Test
    public void mockPathVal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/users/{id}",123))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print()).andReturn();
    }
}

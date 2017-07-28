package com.wusiq.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 用户管理测试类
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src.main.webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = "classpath*:spring/spring-config.xml"),
        @ContextConfiguration(name = "child", locations = "classpath*:spring/spring-mvc.xml")
})
public class UserTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    /*测试添加方法*/
    @Test
    public void testAddUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/userManage/addRow.do"))
                .andExpect(MockMvcResultMatchers.view().name("userListPage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("result"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
    }


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}

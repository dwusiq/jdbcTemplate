package com.wusiq.test;

import com.alibaba.fastjson.JSON;
import com.wusiq.DTO.req.ReqAddRowDto;
import com.wusiq.DTO.req.ReqDeleteRowDto;
import com.wusiq.DTO.req.ReqQueryRowDto;
import com.wusiq.DTO.req.ReqUpdateRowDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    /**
     * 测试添加方法
     */
    @Test
    public void testAddUser() throws Exception {
        //组织参数
        ReqAddRowDto dto = new ReqAddRowDto();
        dto.setUserName("");
        dto.setUserAge(1);

        //参数实体转换成json
        String json = JSON.toJSONString(dto);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/userManage/addRow.do")
                        .contentType(MediaType.APPLICATION_JSON) //数据的格式
                        .content(json)
        )
                .andExpect(MockMvcResultMatchers.view().name("userListPage"))//判断返回的view视图
                .andExpect(MockMvcResultMatchers.model().attributeExists("result"))//判断返回名为“result”的model
                .andDo(MockMvcResultHandlers.print())//打印调试的结果到控制台
                .andReturn();//返回MvcResult参数



        //下面是自定义的结果判断（部分的判断功能在上面的代码已有）
        Assert.assertNotNull(result.getModelAndView().getModel().get("result"));//个人认为可以和上面的attributeExists互换使用
        Assert.assertEquals(result.getModelAndView().getModel().get("result"),"success");
    }


    /**
     * 测试删除方法
     */
    @Test
    public void testDeleteUser() throws Exception {
        //组织参数
        ReqDeleteRowDto dto = new ReqDeleteRowDto();
        dto.setId(12);

        //参数实体转换成json
        String json = JSON.toJSONString(dto);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/userManage/deleteRow.do")
                        .contentType(MediaType.APPLICATION_JSON) //数据的格式
                        .content(json)
        )
                .andDo(MockMvcResultHandlers.print())//打印调试的结果到控制台
                .andReturn();//返回MvcResult参数



        //下面是自定义的结果判断
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertEquals(result.getResponse().getContentAsString(),"success");
    }


    /**
     * 测试修改方法
     */
    @Test
    public void testUpdateRow() throws Exception {
        //组织参数
        ReqUpdateRowDto dto = new ReqUpdateRowDto();
        dto.setId(11);
        dto.setUserNmae("daee");
        dto.setUserAge(32);


        //参数实体转换成json
        String json = JSON.toJSONString(dto);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/userManage/updateRow.do")
                        .contentType(MediaType.APPLICATION_JSON) //数据的格式
                        .content(json)
        )
                .andDo(MockMvcResultHandlers.print())//打印调试的结果到控制台
                .andReturn();//返回MvcResult参数



        //下面是自定义的结果判断（部分的判断功能在上面的代码已有）
        Assert.assertNotNull(result.getResponse().getContentAsString());
        Assert.assertEquals(result.getResponse().getContentAsString(),"success");
    }

    /**
     * 测试查询行
     */
    @Test
    public void testQueryRow() throws Exception {
        //组织参数
        ReqQueryRowDto dto = new ReqQueryRowDto();
        dto.setId(11);

        //参数实体转换成json
        String json = JSON.toJSONString(dto);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/userManage/queryRow.json")
                        .contentType(MediaType.APPLICATION_JSON) //数据的格式
                        .content(json)
        )
                .andDo(MockMvcResultHandlers.print())//打印调试的结果到控制台
                .andReturn();//返回MvcResult参数



        //下面是自定义的结果判断
        Assert.assertNotNull(result.getResponse().getContentAsString());
        System.out.println(result.getResponse().getContentAsString());
    }

    /**
     * 测试查询行
     */
    @Test
    public void testQueryRowList() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/userManage/queryRowList.json")
        )
                .andDo(MockMvcResultHandlers.print())//打印调试的结果到控制台
                .andReturn();//返回MvcResult参数



        //下面是自定义的结果判断
        Assert.assertNotNull(result.getResponse().getContentAsString());
    }


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}

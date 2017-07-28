package com.wusiq.controller;

import com.alibaba.fastjson.JSON;
import com.wusiq.DTO.req.ReqAddRowDto;
import com.wusiq.DTO.req.ReqDeleteRowDto;
import com.wusiq.DTO.req.ReqQueryRowDto;
import com.wusiq.DTO.req.ReqUpdateRowDto;
import com.wusiq.entity.UserEntity;
import com.wusiq.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户管理控制类
 */

@RequestMapping("userManage")
@Controller
public class UserController {
    private static Logger LOGGER =  LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    /*增*/
    @RequestMapping(value = "/addRow.do",method = RequestMethod.GET)
    public ModelAndView addRow(@Validated @RequestBody ReqAddRowDto reqAddRowDto, BindingResult result){
        LOGGER.info("UserController.addRow.start.param:{}", JSON.toJSONString(reqAddRowDto));
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()){
            modelAndView.setViewName("faild");
            modelAndView.addObject("result",result.getAllErrors());
            return modelAndView;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserNmae(reqAddRowDto.getUserName());
        userEntity.setUserAge(reqAddRowDto.getUserAge());
        boolean bool = userService.addRow(userEntity);


        modelAndView.setViewName("userListPage");
        modelAndView.addObject("result",bool?"success":"fail");

        LOGGER.info("UserController.addRow.end.result:{}", bool);
        return modelAndView;
    }

    /*删*/
    @ResponseBody
    @RequestMapping(value = "/deleteRow.do",method = RequestMethod.GET)
    public String deleteRow(@Validated @RequestBody ReqDeleteRowDto reqDeleteRowDto, BindingResult result){
        LOGGER.info("UserController.deleteRow.start.param:{}", JSON.toJSONString(reqDeleteRowDto));
        if (result.hasErrors()){
            return "fail";
        }
        boolean bool = userService.deleteRow(reqDeleteRowDto.getId());
        LOGGER.info("UserController.deleteRow.end.result:{}", bool);
        return bool?"success":"fail";
    }

    /*改*/
    @ResponseBody
    @RequestMapping(value = "/updateRow.do",method = RequestMethod.GET)
    public String updateRow(@Validated @RequestBody ReqUpdateRowDto reqUpdateRowDto, BindingResult result){
        LOGGER.info("UserController.updateRow.start.param:{}", JSON.toJSONString(reqUpdateRowDto));
        if (result.hasErrors()){
            return "fail";
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(reqUpdateRowDto.getId());
        userEntity.setUserNmae(reqUpdateRowDto.getUserNmae());
        userEntity.setUserAge(reqUpdateRowDto.getUserAge());
        boolean bool = userService.updateRow(userEntity);
        LOGGER.info("UserController.updateRow.end.result:{}", bool);
        return bool?"success":"fail";
    }

    /*查*/
    @ResponseBody
    @RequestMapping(value = "/queryRow.json",method = RequestMethod.GET)
    public String queryRow(@Validated @RequestBody ReqQueryRowDto reqQueryRowDto, BindingResult result){
        LOGGER.info("UserController.queryRow.start.param:{}", JSON.toJSONString(reqQueryRowDto));
        UserEntity userEntity = userService.queryRow(reqQueryRowDto.getId());
        LOGGER.info("UserController.queryRow.end.result:{}", JSON.toJSONString(userEntity));
        return JSON.toJSONString(userEntity);
    }

    /*查list*/
    @ResponseBody
    @RequestMapping(value = "/queryRowList.json",method = RequestMethod.GET)
    public String queryRowList(){
        LOGGER.info("UserController.queryRowList.start...");
        List<UserEntity> list = userService.queryRowList();
        LOGGER.info("UserController.queryRowList.end.result:{}", JSON.toJSONString(list));
        return JSON.toJSONString(list);
    }
}


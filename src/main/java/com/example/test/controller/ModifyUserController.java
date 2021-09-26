package com.example.test.controller;

import com.example.test.bean.UserBean;
import com.example.test.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "用户操作增删改")
public class ModifyUserController {

    @Autowired
    UserService userService;

    /**
     * 查询所有用户信息
     * @param modelMap
     * @return
     */
    @GetMapping("/users")
    public String showUsers(ModelMap modelMap){
        List<UserBean> userList = userService.queryAllUser();
        modelMap.addAttribute("userList",userList);
        return "users";
    }

    /**
     * 新增用户
     * remark：这里未做用户名是否重复校验
     * @param userBean
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    public Map addUser(UserBean a){
        int flag = userService.addUser(a);
        Map<String,Object> map = new HashMap<String,Object>();
        if(flag == 1){
            map.put("msg","新增用户成功");
            return map;
        }else {
            map.put("msg","新增用户失败");
            return map;
        }
    }


    /**
     * 根据用户ID删除用户信息
     * @param id
     * @param modelMap
     * @return
     */
    @PostMapping(value = "/deleteUser+{id}")
    public String dropUser(@PathVariable("id") String id,ModelMap modelMap){
        int flag = userService.dropUser(id);
        List<UserBean> userList = userService.queryAllUser();
        modelMap.addAttribute("userList",userList);
        if(flag == 1){
            return "users";
        }else {
            return "error";
        }
    }

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    @GetMapping("/user+{id}")
    public String queryUser(@PathVariable("id") String id,ModelMap modelMap){
        UserBean userBean = userService.queryUserById(id);
        modelMap.addAttribute("user",userBean);
        return "userInfo";
    }

    /**
     * 根据用户ID修改用户信息
     * remark：这里未做用户名是否重复校验
     * @param userBean
     * @return
     */
    @PostMapping("/modifyUser")
    @ResponseBody
    public Map modifyUser(UserBean userBean){
        int flag = userService.modifyUser(userBean);
        Map<String,Object> map = new HashMap<>();
        if(flag == 1){
            map.put("msg","修改用户信息成功");
            return map;
        }else {
            map.put("msg","修改用户信息失败");
            return map;
        }
    }

}

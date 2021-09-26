package com.example.test.controller;

import com.example.test.bean.JsonBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@Api(tags = "简单的数据库增删改查")
@RestController
public class JsonController {
    @GetMapping("/getJson") //要具体到请求方法，不然swagger会把所有的方法给你列出来的
    @ResponseBody
    @ApiOperation(value="获取用户列表", notes="")
    public JsonBean getJson(){
        JsonBean json = new JsonBean();
        json.setName("panpan");
        json.setDealDate(new Date());
        return json;
    }

}

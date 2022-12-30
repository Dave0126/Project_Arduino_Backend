package fr.gdai.ap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
    @RequestMapping("/save")
    // 2.3 	设置当前操作的返回值类型, 将该方法的返回值作为响应体返回给浏览器
    @ResponseBody
    public String save() {
        System.out.println("user saved!");
        return "{'module':'springmvc:save()'}";
    }

    @RequestMapping("/delect")
    @ResponseBody
    public String delect() {
        System.out.println("user delected!");
        return "{'module':'springmvc:delect()'}";
    }
}


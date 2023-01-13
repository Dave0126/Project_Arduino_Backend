package fr.gdai.ap.controller;

import fr.gdai.ap.domain.User;
import fr.gdai.ap.service.impl.UserServiceImpl;
import fr.gdai.ap.utils.Result;
import fr.gdai.ap.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping("/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        Integer resultCode;
        String msg;
        if(userService.register(user)) {
            resultCode = ResultCode.INSERT_SUCC;
            msg = "register successfully";
        } else {
            resultCode = ResultCode.INSERT_ERR;
            msg = "register failed";
        }
        return new Result(resultCode, null, msg);
    }

    @RequestMapping("/addProduct")
    @ResponseBody
    public Result addProductByUser(String username, String barcode){
        String msg = userService.addProductByUser(username, barcode);
        return new Result(ResultCode.INSERT_SUCC, userService.showUserByName(username), msg);
    }
}

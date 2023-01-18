package fr.gdai.ap.controller;

import fr.gdai.ap.domain.User;
import fr.gdai.ap.service.impl.UserServiceImpl;
import fr.gdai.ap.utils.Result;
import fr.gdai.ap.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
            resultCode = ResultCode.USER_INSERT_SUCC;
            msg = "User register successfully";
        } else {
            resultCode = ResultCode.USER_EXIST;
            msg = "User register failed: This user is existed";
        }
        return new Result(resultCode, null, msg);
    }

    @RequestMapping("/addProduct")
    @ResponseBody
    // TODO 尝试使用状态码区分msg，将更改相应Service内的代码
    public Result addProductByUser(String username, String barcode){
        Integer code = userService.addProductByUser(username, barcode);
        String msg;
        if(code == ResultCode.BARCODE_NOTFOUND) {msg = "The barcode you entered does not exist!";}
        else if(code == ResultCode.USER_NOTFOUND) {msg = "The username you entered does not exist!";}
        else if(code == ResultCode.INSERT_ERR) {msg = "Failed to add product for user";}
        else {msg =  "Successfully add product for user";}
        return new Result(code, userService.showUserByName(username), msg);
    }
}

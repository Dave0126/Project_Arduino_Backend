package fr.gdai.ap.service.impl;

import fr.gdai.ap.domain.MyProduct;
import fr.gdai.ap.domain.User;
import fr.gdai.ap.service.UserService;
import fr.gdai.ap.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    Map<String,User> userMap = new HashMap<>();

    @Autowired
    private MyProduct product;

    @Autowired
    private User user;

    @Autowired
    private MyProductServiceImpl myProductService;

    @Override
    public boolean register(User user) {
        boolean result = false;
        if(user.getName() == null){
            return result;
        }
        if ( !userMap.containsKey(user.getName())) {
            userMap.put(user.getName(), user);
            result = true;
        }
        System.out.println("size of userMap =" + userMap.size());
        return result;
    }

    @Override
    public Integer addProductByUser(String name, String barcode) {
        if ( !userMap.containsKey(name)) {
//            return "The username you entered does not exist!";
            return ResultCode.USER_NOTFOUND;
        }
        User userP = userMap.get(name);
        product = myProductService.transferOpenStackProduct2MyProduct(barcode);
        if( product == null){
//            return "The barcode you entered does not exist!";
            return  ResultCode.BARCODE_NOTFOUND;
        }
        if(!userP.getProductList().add(product)){
//            return "Failed to add product for user : " + name;
            return ResultCode.INSERT_ERR;
        }
        System.out.println(userP);
//        return "Successfully add product for user : " + name;
        return  ResultCode.INSERT_SUCC;
    }

    public User showUserByName(String name){
        return userMap.get(name);
    }

}

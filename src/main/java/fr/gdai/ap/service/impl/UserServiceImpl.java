package fr.gdai.ap.service.impl;

import fr.gdai.ap.domain.MyProduct;
import fr.gdai.ap.domain.User;
import fr.gdai.ap.service.UserService;
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
    public boolean register(User newUser) {
        if(newUser.getName() == null){
            return false;
        }
        if ( !userMap.containsKey(user.getName())) {
            userMap.put(user.getName(), user);
        }
        System.out.println("size of userMap =" + userMap.size());
        return true;
    }

    @Override
    public void addProductByUser(String barcode) {
        product = myProductService.transferOpenStackProduct2MyProduct(barcode);
        user.getProductList().add(product);
        System.out.println(user);
    }

}

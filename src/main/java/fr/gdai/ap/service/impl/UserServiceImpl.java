package fr.gdai.ap.service.impl;

import fr.gdai.ap.domain.MyNutriment;
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
        /**
         * 解决ArrayList中add()方法会覆盖之前对象的错误
         * 问题: 个人认为是对象引用的问题，所以在此new一个新对象，在add()这个新对象
         */
        MyProduct productTemp = new MyProduct();
        productTemp.setBarcode(product.getBarcode());
        productTemp.setName(product.getName());
        productTemp.setQuantity(product.getQuantity());
        productTemp.setNutriments(new MyNutriment(product.getNutriments().getCalcium(),
                product.getNutriments().getCarbohydrates(),
                product.getNutriments().getEnergy()));
        productTemp.setIngredients(product.getIngredients());

        if(!userP.getProductList().add(productTemp)){
//            return "Failed to add product for user : " + name;
            return ResultCode.INSERT_ERR;
        }
        /**
         * 每日营养总合
         */
        userP.setDailyEnergy(userP.getDailyEnergy() +
                this.product.getNutriments().getEnergy() * (Integer.parseInt(this.product.getQuantity())/100));
        userP.setDailyCalcium(userP.getDailyCalcium() +
                this.product.getNutriments().getCalcium() * (Float.parseFloat(this.product.getQuantity())/100));
        userP.setDailyCarbohydrates(userP.getDailyCarbohydrates() +
                this.product.getNutriments().getCarbohydrates() * (Float.parseFloat(this.product.getQuantity())/100));

        System.out.println(userP);
//        return "Successfully add product for user : " + name;
        return  ResultCode.INSERT_SUCC;
    }

    public User showUserByName(String name){
        return userMap.get(name);
    }

}

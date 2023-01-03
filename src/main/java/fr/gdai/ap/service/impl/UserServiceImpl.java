package fr.gdai.ap.service.impl;

import fr.gdai.ap.domain.MyProduct;
import fr.gdai.ap.domain.User;
import fr.gdai.ap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MyProduct product;

    @Autowired
    private User user;

    @Autowired
    private MyProductServiceImpl myProductService;

    @Override
    public void addProductByUser(String barcode) {
        product = myProductService.transferOpenStackProduct2MyProduct(barcode);
        user.getProductList().add(product);
        System.out.println(user);
    }
}

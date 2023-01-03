package fr.gdai.ap.service.impl;

import fr.gdai.ap.config.SpringConfig;
import fr.gdai.ap.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    User user;

    @Test
    public void addProductByUser() {
        String barcode = "3228886043714";
        userService.addProductByUser(barcode);
    }

    @Test
    public void register() {
        User userTest1 = new User("gdai", 24);
        System.out.println(userService.register(userTest1));

        User userTest2 = new User("gdai", 25);
        System.out.println(userService.register(userTest2));

        User userTest3 = new User("guohao", 25);
        System.out.println(userService.register(userTest3));
    }

    @Test
    public void modifyUserAndAddProduct(){
        user.setName("test");
        user.setAge(20);
        String barcode = "3228886043714";
        userService.addProductByUser(barcode);
    }
}
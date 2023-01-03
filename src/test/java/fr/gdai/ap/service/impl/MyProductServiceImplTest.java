package fr.gdai.ap.service.impl;

import fr.gdai.ap.config.SpringConfig;
import fr.gdai.ap.domain.MyProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class MyProductServiceImplTest {

    @Autowired
    private MyProductServiceImpl productService;

    @Test
    public void transferOpenStackProduct2MyProduct() {
        String barcode = "3228886043714";
        MyProduct p = productService.transferOpenStackProduct2MyProduct(barcode);
        System.out.println(p);
    }
}
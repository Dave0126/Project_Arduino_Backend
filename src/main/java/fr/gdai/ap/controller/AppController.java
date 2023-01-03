package fr.gdai.ap.controller;

import fr.gdai.ap.domain.MyProduct;
import fr.gdai.ap.service.impl.MyProductServiceImpl;
import fr.gdai.ap.utils.Result;
import fr.gdai.ap.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class AppController {

    @Autowired
    private MyProductServiceImpl productService;

    @GetMapping("/{barcode}")
    public Result getProduct(@PathVariable String barcode) {
        Integer resultCode;
        String msg;
        MyProduct product = productService.transferOpenStackProduct2MyProduct(barcode);
        if(product == null){
            resultCode = ResultCode.SELECT_ERR;
            msg = "product NOT found";
        } else {
            resultCode = ResultCode.SELECT_SUCC;
            msg = "product found";
        }
        return new Result(resultCode, product, msg);
    }

}


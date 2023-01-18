package fr.gdai.ap.service.impl;

import fr.gdai.ap.domain.MyNutriment;
import fr.gdai.ap.domain.MyProduct;
import fr.gdai.ap.service.MyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.OpenFoodFactsWrapper;

import java.util.Arrays;

@Service
public class MyProductServiceImpl implements MyProductService {

    @Autowired
    private OpenFoodFactsWrapper wrapper;

    @Autowired
    private MyProduct myProduct;

    @Autowired
    private MyNutriment productNutriment;

    @Override
    public MyProduct transferOpenStackProduct2MyProduct(String barcode) {
        ProductResponse productResponse = wrapper.fetchProductByCode(barcode);
        if (!productResponse.isStatus()) {
            System.out.println("Status: " + productResponse.getStatusVerbose());
            return null;
        }
        productNutriment.setCalcium(productResponse.getProduct().getNutriments().getCalcium100G());
        productNutriment.setCarbohydrates(productResponse.getProduct().getNutriments().getCarbohydrates100G());
        productNutriment.setEnergy(productResponse.getProduct().getNutriments().getEnergyKcal());

        myProduct.setBarcode(barcode);
        myProduct.setName(productResponse.getProduct().getProductName());
        myProduct.setQuantity(productResponse.getProduct().getProductQuantity());
        myProduct.setIngredients(Arrays.copyOfRange(productResponse.getProduct().getIngredients(),0,5));
        myProduct.setNutriments(productNutriment);

        return myProduct;
    }
}

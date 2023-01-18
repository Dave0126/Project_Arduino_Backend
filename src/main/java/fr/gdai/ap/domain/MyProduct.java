package fr.gdai.ap.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.coderion.model.Ingredient;
import pl.coderion.model.Nutriments;
import pl.coderion.model.Product;
import pl.coderion.model.ProductResponse;
import pl.coderion.service.OpenFoodFactsWrapper;

import java.util.Arrays;

@Component
public class MyProduct {
    private String name;
    private String barcode;
    /**
     * Top 5 ingredients
     */
    private Ingredient[] ingredients;
    private String quantity;
    @Autowired
    private MyNutriment nutriments;

    public MyProduct() {
    }

    public MyProduct(String name, String barcode, Ingredient[] ingredients, String quantity, MyNutriment nutriments) {
        this.name = name;
        this.barcode = barcode;
        this.ingredients = ingredients;
        this.quantity = quantity;
        this.nutriments = nutriments;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public MyNutriment getNutriments() {
        return nutriments;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setIngredients(Ingredient[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setNutriments(MyNutriment nutriments) {
        this.nutriments = nutriments;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "MyProduct{" +
                "name='" + name + '\'' +
                ", barcode='" + barcode + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", quantity='" + quantity + '\'' +
                ", nutriments=" + nutriments +
                '}';
    }
}

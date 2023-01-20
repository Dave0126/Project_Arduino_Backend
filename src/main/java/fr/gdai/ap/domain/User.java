package fr.gdai.ap.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class User {
    private String name;
    private int age;
    @Autowired
    private List<MyProduct> productList;
    int dailyEnergy;
    float dailyCalcium;
    float dailyCarbohydrates;

    public User() {
        this.productList = new ArrayList<>();
        dailyEnergy = 0;
        dailyCalcium = 0;
        dailyCarbohydrates = 0;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.productList = new ArrayList<>();
        dailyEnergy = 0;
        dailyCalcium = 0;
        dailyCarbohydrates = 0;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<MyProduct> getProductList() {
        return productList;
    }

    public int getDailyEnergy() { return dailyEnergy; }

    public float getDailyCalcium() { return dailyCalcium; }

    public float getDailyCarbohydrates() { return dailyCarbohydrates; }

    public void setName(String name) { this.name = name; }

    public void setAge(int age) { this.age = age; }

    public void setProductList(List<MyProduct> productList) { this.productList = productList;}

    public void setDailyEnergy(int dailyEnergy) { this.dailyEnergy = dailyEnergy; }

    public void setDailyCalcium(float dailyCalcium) { this.dailyCalcium = dailyCalcium; }

    public void setDailyCarbohydrates(float dailyCarbohydrates) { this.dailyCarbohydrates = dailyCarbohydrates; }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", productList=" + productList +
                ", dailyEnergy=" + dailyEnergy +
                ", dailyCalcium=" + dailyCalcium +
                ", dailyCarbohydrates=" + dailyCarbohydrates +
                '}';
    }
}

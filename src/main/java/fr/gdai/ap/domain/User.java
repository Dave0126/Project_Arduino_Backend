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

    public User() {
        this.productList = new ArrayList<>();
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.productList = new ArrayList<>();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", productList=" + productList +
                '}';
    }
}

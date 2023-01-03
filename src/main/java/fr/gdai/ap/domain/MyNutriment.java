package fr.gdai.ap.domain;

import org.springframework.stereotype.Component;

/**
 * Nutriment per 100 g/ml
 */
@Component
public class MyNutriment {
    /**
     * calcium, unit: gram
     */
    private float calcium;
    /**
     * carbohydrates, unit: gram
     */
    private float carbohydrates;
    /**
     * energy, unit: kcal
     */
    private int energy;

    public MyNutriment(float calcium, float carbohydrates, int energy) {
        this.calcium = calcium;
        this.carbohydrates = carbohydrates;
        this.energy = energy;
    }

    public MyNutriment() {
    }

    public float getCalcium() {
        return calcium;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public int getEnergy() {
        return energy;
    }

    public void setCalcium(float calcium) {
        this.calcium = calcium;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "MyNutriment{" +
                "calcium=" + calcium +
                ", carbohydrates=" + carbohydrates +
                ", energy=" + energy +
                '}';
    }
}

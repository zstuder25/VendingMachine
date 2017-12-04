package com.zach.kata.objects;

/**
 * Created by zmans on 12/3/2017.
 */
public class Coin {

    private double coinWeight;
    private double coinDiameter;

    public Coin(double weight, double diameter) {
        coinWeight = weight;
        coinDiameter = diameter;
    }

    public void setCoinWeight(double coinWeight) {
        this.coinWeight = coinWeight;
    }

    public double getCoinWeight() {
        return coinWeight;
    }

    public double getCoinDiameter() {
        return coinDiameter;
    }

    public void setCoinDiameter(double coinDiameter) {
        this.coinDiameter = coinDiameter;
    }
}

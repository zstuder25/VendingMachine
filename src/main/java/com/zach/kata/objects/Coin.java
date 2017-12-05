package com.zach.kata.objects;

/**
 * Created by zmans on 12/3/2017.
 */
public class Coin {

    private double coinWeight;
    private double coinDiameter;

    private enum Coins {
        PENNY(2.500, 0.750),
        NICKEL(5.000, 0.835),
        DIME(2.268, 0.705),
        QUARTER(5.670, 0.955);

        private final double weight;
        private final double diameter;

        Coins(double weight, double diameter){
            this.weight = weight;
            this.diameter = diameter;
        }

        public double getWeight(){return weight;}
        public double getDiameter(){return diameter;}
    }

    public Coin(String coinString){
        Coins coin = Coins.valueOf(coinString);
        coinWeight = coin.getWeight();
        coinDiameter = coin.getDiameter();
    }

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

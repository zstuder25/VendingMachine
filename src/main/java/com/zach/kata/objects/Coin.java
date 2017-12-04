package com.zach.kata.objects;

/**
 * Created by zmans on 12/3/2017.
 */
public class Coin {

    String coinName;

    public Coin(double weight, double diameter) {
        if(weight == 2.5 && diameter == 0.75){
            setCoinName("Penny");
        }else if(weight == 5.0 && diameter == 0.835){
            setCoinName("Nickel");
        }
    }


    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinName() {
        return coinName;
    }
}

package com.zach.kata.objects;


import static com.zach.kata.constants.Constants.Coin.NICKEL_D;
import static com.zach.kata.constants.Constants.Coin.NICKEL_W;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachine {



    public String insertCoin(Coin coin){
        if(coin.getCoinWeight() == NICKEL_W && coin.getCoinDiameter() == NICKEL_D){
            return("$0.05");
        }
        return "";
    }
}

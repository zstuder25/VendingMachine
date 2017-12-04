package com.zach.kata.objects;


import static com.zach.kata.constants.Constants.Coin.*;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachine {



    public String insertCoin(Coin coin){
        if(coin.getCoinWeight() == NICKEL_W && coin.getCoinDiameter() == NICKEL_D){
            return "$0.05";
        }else if(coin.getCoinWeight() == DIME_W && coin.getCoinDiameter() == DIME_D){
            return "$0.10";
        }
        return "";
    }
}

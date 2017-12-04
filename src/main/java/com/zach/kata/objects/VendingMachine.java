package com.zach.kata.objects;


import java.text.NumberFormat;
import java.util.Locale;

import static com.zach.kata.constants.Constants.Coin.*;
import static com.zach.kata.constants.Constants.VendingMachine.INSERT_COIN;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachine {

    private double currentAmount = 0;
    private static NumberFormat formatter =  NumberFormat.getCurrencyInstance(new Locale("en", "US"));

    private String convertAmount(){
        return formatter.format(currentAmount);
    }

    public String insertCoin(Coin coin){
        if(coin.getCoinWeight() == NICKEL_W && coin.getCoinDiameter() == NICKEL_D){
            currentAmount += 0.05;
            return convertAmount();
        }else if(coin.getCoinWeight() == DIME_W && coin.getCoinDiameter() == DIME_D){
            currentAmount += 0.10;
            return convertAmount();
        }else if(coin.getCoinWeight() == QUARTER_W && coin.getCoinDiameter() == QUARTER_D){
            currentAmount += 0.25;
            return convertAmount();
        }
        return INSERT_COIN;
    }
}

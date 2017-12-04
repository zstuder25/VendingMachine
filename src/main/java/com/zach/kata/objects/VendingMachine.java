package com.zach.kata.objects;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.zach.kata.constants.Constants.Coin.DIME_D;
import static com.zach.kata.constants.Constants.Coin.DIME_W;
import static com.zach.kata.constants.Constants.Coin.NICKEL_D;
import static com.zach.kata.constants.Constants.Coin.NICKEL_W;
import static com.zach.kata.constants.Constants.Coin.QUARTER_D;
import static com.zach.kata.constants.Constants.Coin.QUARTER_W;
import static com.zach.kata.constants.Constants.VendingMachine.INSERT_COIN;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachine {

    private double currentAmount = 0;
    private static NumberFormat formatter =  NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    private static ArrayList<Coin> rejectedCoins = new ArrayList<Coin>();

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
        }else{
            rejectedCoins.add(coin);
            if(currentAmount == 0){
                return INSERT_COIN;
            }else{
                return  convertAmount();
            }
        }
    }

    public ArrayList<Coin> getRejectedCoins() {
        return rejectedCoins;
    }
}

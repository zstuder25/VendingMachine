package com.zach.kata.objects;

import static com.zach.kata.constants.Constants.Coin.*;

/**
 * Created by zmans on 12/3/2017.
 */
public class Coin {

    String coinName;

    public Coin(double weight, double diameter) {
        if(weight == PENNY_W && diameter == PENNY_D){
            setCoinName(PENNY);
        }else if(weight == NICKEL_W && diameter == NICKEL_D){
            setCoinName(NICKEL);
        }else if(weight == DIME_W && diameter == DIME_D){
            setCoinName(DIME);
        }else if(weight == QUARTER_W && diameter == QUARTER_D){
            setCoinName(QUARTER);
        }else{
            setCoinName(UNKNOWN);
        }
    }


    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinName() {
        return coinName;
    }
}

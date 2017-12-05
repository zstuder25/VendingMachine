package com.zach.kata.constants;

/**
 * Created by zmans on 12/3/2017.
 */
public interface Constants {

    interface Coin {
        double PENNY_W = 2.500;
        double PENNY_D = 0.750;
        double NICKEL_W = 5.000;
        double NICKEL_D = 0.835;
        double DIME_W = 2.268;
        double DIME_D = 0.705;
        double QUARTER_W = 5.670;
        double QUARTER_D = 0.955;
    }

    interface VendingMachine {
        String INSERT_COIN = "INSERT COIN";
        String PRICE = "PRICE ";
    }
}

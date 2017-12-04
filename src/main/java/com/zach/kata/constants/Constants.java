package com.zach.kata.constants;

/**
 * Created by zmans on 12/3/2017.
 */
public interface Constants {

    interface Coin {
        String PENNY = "Penny";
        String NICKEL = "Nickel";
        String DIME = "Dime";
        String QUARTER = "Quarter";
        String UNKNOWN = "Unknown";

        Double PENNY_W = 2.500;
        Double PENNY_D = 0.750;
        Double NICKEL_W = 5.000;
        Double NICKEL_D = 0.835;
        Double DIME_W = 2.268;
        Double DIME_D = 0.705;
        Double QUARTER_W = 5.670;
        Double QUARTER_D = 0.955;
    }

    interface VendingMachine {
        String INSERT_COIN = "INSERT COIN";
    }
}

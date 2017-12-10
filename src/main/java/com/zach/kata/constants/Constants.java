package com.zach.kata.constants;

/**
 * Created by zmans on 12/3/2017.
 */
public interface Constants {

    interface Coin {
        String PENNY = "PENNY";
        String NICKEL = "NICKEL";
        String DIME = "DIME";
        String QUARTER = "QUARTER";

        String NICKEL_VAL = "0.05";
        String DIME_VAL = "0.10";
        String QUARTER_VAL = "0.25";

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
        String COLA = "COLA";
        String CHIPS = "CHIPS";
        String CANDY = "CANDY";

        String COLA_VAL = "1.00";
        String CHIPS_VAL = "0.50";
        String CANDY_VAL = "0.65";

        String INSERT_COIN = "INSERT COIN";
        String PRICE = "PRICE ";
        String THANK_YOU = "THANK YOU";
        String SOLD_OUT = "SOLD OUT";
        String EXACT_CHANGE = "EXACT CHANGE ONLY";
    }

    interface CustomerInteraction{
        String CHECK = "CHECK";
        String RETURN = "RETURN";
        String QUIT = "Q";
    }
}

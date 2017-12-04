package com.zach.kata.objects;

import org.junit.Test;

import static com.zach.kata.constants.Constants.Coin.NICKEL_D;
import static com.zach.kata.constants.Constants.Coin.NICKEL_W;
import static org.junit.Assert.assertEquals;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachineTest {

    @Test
    public void acceptCoinTest(){
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("", vendingMachine.insertCoin(new Coin(0, 0)));
    }

    @Test
    public void insertValidCoinNickel(){
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("$0.05", vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D)));
    }
}

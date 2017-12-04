package com.zach.kata.objects;

import org.junit.Test;

import static com.zach.kata.constants.Constants.Coin.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachineTest {

    @Test
    public void acceptCoinTest(){
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("INSERT COIN", vendingMachine.insertCoin(new Coin(0, 0)));
    }

    @Test
    public void insertValidCoinNickel(){
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("$0.05", vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D)));
    }

    @Test
    public void insertValidCoinDime(){
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("$0.10", vendingMachine.insertCoin(new Coin(DIME_W, DIME_D)));
    }

    @Test
    public void insertValidCoinQuarter(){
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("$0.25", vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D)));
    }

    @Test
    public void insertInvalidCoinPenny(){
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("INSERT COIN", vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D)));
    }

    @Test
    public void insertInvalidCoinUnknown(){
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("INSERT COIN", vendingMachine.insertCoin(new Coin(1, 1)));
    }

    @Test
    public void insertMultipleValidCoins(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        assertEquals("$0.30", vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D)));
    }

    @Test
    public void insertMultipleValidCoins2(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        assertEquals("$0.50", vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D)));
    }
}

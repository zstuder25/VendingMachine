package com.zach.kata.objects;

import org.junit.Test;

import static com.zach.kata.constants.Constants.Coin.DIME_D;
import static com.zach.kata.constants.Constants.Coin.DIME_W;
import static com.zach.kata.constants.Constants.Coin.NICKEL_D;
import static com.zach.kata.constants.Constants.Coin.NICKEL_W;
import static com.zach.kata.constants.Constants.Coin.PENNY_D;
import static com.zach.kata.constants.Constants.Coin.PENNY_W;
import static com.zach.kata.constants.Constants.Coin.QUARTER_D;
import static com.zach.kata.constants.Constants.Coin.QUARTER_W;
import static com.zach.kata.constants.Constants.VendingMachine.INSERT_COIN;
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

    @Test
    public void returnInvalidPennyCoin(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        assertEquals(PENNY_W, vendingMachine.getRejectedCoins().get(0).getCoinWeight(), 0.001);
    }

    @Test
    public void returnInvalidCoinAndDisplayAmount(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        assertEquals("$0.15", vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D)));
        assertEquals(PENNY_W, vendingMachine.getRejectedCoins().get(0).getCoinWeight(), 0.001);
    }

    @Test
    public void returnInvalidCoinAndDisplayAmount2(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        assertEquals("$0.15", vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D)));
        assertEquals(PENNY_W, vendingMachine.getRejectedCoins().get(0).getCoinWeight(), 0.001);
        assertEquals("$0.40", vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D)));
    }

    @Test
    public void returnMultipleInvalidCoinsAndDisplayAmountOfValid(){
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals(INSERT_COIN, vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D)));
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        vendingMachine.insertCoin(new Coin(0.1, 1.5));
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        String finalAmount = vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        assertEquals(3, vendingMachine.getRejectedCoins().size());
        assertEquals("$0.40", finalAmount);
    }

    @Test
    public void clearInputMethodTest(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.clear();
        assertEquals("$0.05", vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D)));
    }

    @Test
    public void clearRejectedCoinsTest(){
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        assertEquals(0, vendingMachine.getRejectedCoins().size());
    }
}

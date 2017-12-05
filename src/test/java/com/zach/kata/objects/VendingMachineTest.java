package com.zach.kata.objects;

import org.junit.Before;
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
import static com.zach.kata.constants.Constants.VendingMachine.PRICE;
import static com.zach.kata.constants.Constants.VendingMachine.THANK_YOU;
import static org.junit.Assert.assertEquals;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Before
    public void setup(){
        vendingMachine = new VendingMachine();
    }

    //Feature Accept Coins Testing
    @Test
    public void acceptCoinTest(){
        vendingMachine.insertCoin(new Coin(0, 0));
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertValidCoinNickel(){
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        assertEquals("$0.05", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertValidCoinDime(){
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        assertEquals("$0.10", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertValidCoinQuarter(){
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        assertEquals("$0.25", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertInvalidCoinPenny(){
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertInvalidCoinUnknown(){
        vendingMachine.insertCoin(new Coin(1, 1));
        assertEquals("INSERT COIN", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertMultipleValidCoins(){
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        assertEquals("$0.30", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertMultipleValidCoins2(){
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        assertEquals("$0.50", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void returnInvalidPennyCoin(){
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        assertEquals(PENNY_W, vendingMachine.getRejectedCoins().get(0).getCoinWeight(), 0.001);
        vendingMachine.clear();
    }

    @Test
    public void returnInvalidCoinAndDisplayAmount(){
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        assertEquals("$0.15", vendingMachine.getDisplay());
        assertEquals(PENNY_W, vendingMachine.getRejectedCoins().get(0).getCoinWeight(), 0.001);
        vendingMachine.clear();
    }

    @Test
    public void returnInvalidCoinAndDisplayAmount2(){
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        assertEquals("$0.15", vendingMachine.getDisplay());
        assertEquals(PENNY_W, vendingMachine.getRejectedCoins().get(0).getCoinWeight(), 0.001);
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        assertEquals("$0.40", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void returnMultipleInvalidCoinsAndDisplayAmountOfValid(){
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        vendingMachine.insertCoin(new Coin(0.1, 1.5));
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        String finalAmount = vendingMachine.getDisplay();
        assertEquals(3, vendingMachine.getRejectedCoins().size());
        assertEquals("$0.40", finalAmount);
        vendingMachine.clear();
    }

    @Test
    public void clearInputMethodTest(){
        vendingMachine.insertCoin(new Coin(DIME_W, DIME_D));
        vendingMachine.clear();
        vendingMachine.insertCoin(new Coin(NICKEL_W, NICKEL_D));
        assertEquals("$0.05", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void clearRejectedCoinsTest(){
        vendingMachine.insertCoin(new Coin(PENNY_W, PENNY_D));
        vendingMachine.clear();
        assertEquals(0, vendingMachine.getRejectedCoins().size());
    }

    //Feature Select Product Testing
    @Test
    public void selectProductTest(){
        vendingMachine.selectProduct("COLA");
        assertEquals("COLA", vendingMachine.getSelectedProduct());
    }

    @Test
    public void selectColaProductWithNoMoneyTest(){
        vendingMachine.selectProduct("COLA");
        assertEquals("PRICE $1.00", vendingMachine.getDisplay());
    }

    @Test
    public void selectChipsProductWithNoMoneyTest(){
        vendingMachine.selectProduct("CHIPS");
        assertEquals("PRICE $0.50", vendingMachine.getDisplay());
    }

    @Test
    public void selectCandyProductWithNoMoneyTest(){
        vendingMachine.selectProduct("CANDY");
        assertEquals("PRICE $0.65", vendingMachine.getDisplay());
    }

    @Test
    public void selectChipsWithMoneyTest(){
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.selectProduct("CHIPS");
        assertEquals("THANK YOU", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void selectCandyProductWithNoMoneyAndCheckDisplayTwiceTest(){
        vendingMachine.selectProduct("COLA");
        assertEquals("PRICE $1.00", vendingMachine.getDisplay());
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
    }

    @Test
    public void selectColaWithSufficientMoneyAndCheckDisplayTwiceTest(){
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.selectProduct("COLA");
        assertEquals(THANK_YOU, vendingMachine.getDisplay());
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void selectChipsWithSufficientMoneyAndCheckDisplayTwiceAndAmountTest(){
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.selectProduct("CHIPS");
        assertEquals(THANK_YOU, vendingMachine.getDisplay());
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
        assertEquals(0.0, vendingMachine.getCurrentAmount(), 0.001);
        vendingMachine.clear();
    }

    @Test
    public void selectChipsWithoutSufficientMoneyAndCheckDisplayTwiceAndAmountTest(){
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.insertCoin(new Coin(QUARTER_W, QUARTER_D));
        vendingMachine.selectProduct("COLA");
        assertEquals(PRICE + "$1.00", vendingMachine.getDisplay());
        assertEquals("$0.50", vendingMachine.getDisplay());
        vendingMachine.clear();
    }
}

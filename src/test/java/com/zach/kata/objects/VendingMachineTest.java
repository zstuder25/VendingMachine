package com.zach.kata.objects;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.zach.kata.constants.Constants.Coin.DIME;
import static com.zach.kata.constants.Constants.Coin.DIME_D;
import static com.zach.kata.constants.Constants.Coin.DIME_W;
import static com.zach.kata.constants.Constants.Coin.NICKEL;
import static com.zach.kata.constants.Constants.Coin.NICKEL_W;
import static com.zach.kata.constants.Constants.Coin.PENNY;
import static com.zach.kata.constants.Constants.Coin.PENNY_W;
import static com.zach.kata.constants.Constants.Coin.QUARTER;
import static com.zach.kata.constants.Constants.Coin.QUARTER_W;
import static com.zach.kata.constants.Constants.VendingMachine.CANDY;
import static com.zach.kata.constants.Constants.VendingMachine.CHIPS;
import static com.zach.kata.constants.Constants.VendingMachine.COLA;
import static com.zach.kata.constants.Constants.VendingMachine.INSERT_COIN;
import static com.zach.kata.constants.Constants.VendingMachine.PRICE;
import static com.zach.kata.constants.Constants.VendingMachine.SOLD_OUT;
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
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertValidCoinNickel(){
        vendingMachine.insertCoin(new Coin(NICKEL));
        assertEquals("$0.05", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertValidCoinDime(){
        vendingMachine.insertCoin(new Coin(DIME));
        assertEquals("$0.10", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertValidCoinQuarter(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        assertEquals("$0.25", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertInvalidCoinPenny(){
        vendingMachine.insertCoin(new Coin(PENNY));
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertInvalidCoinUnknown(){
        vendingMachine.insertCoin(new Coin(1, 1));
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertMultipleValidCoins(){
        vendingMachine.insertCoin(new Coin(NICKEL));
        vendingMachine.insertCoin(new Coin(QUARTER));
        assertEquals("$0.30", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void insertMultipleValidCoins2(){
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.insertCoin(new Coin(NICKEL));
        vendingMachine.insertCoin(new Coin(QUARTER));
        assertEquals("$0.50", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void returnInvalidPennyCoin(){
        vendingMachine.insertCoin(new Coin(PENNY));
        assertEquals(PENNY_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
        vendingMachine.clear();
    }

    @Test
    public void returnInvalidCoinAndDisplayAmount(){
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.insertCoin(new Coin(NICKEL));
        vendingMachine.insertCoin(new Coin(PENNY));
        assertEquals("$0.15", vendingMachine.getDisplay());
        assertEquals(PENNY_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
        vendingMachine.clear();
    }

    @Test
    public void returnInvalidCoinAndDisplayAmount2(){
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.insertCoin(new Coin(NICKEL));
        vendingMachine.insertCoin(new Coin(PENNY));
        assertEquals("$0.15", vendingMachine.getDisplay());
        assertEquals(PENNY_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
        vendingMachine.insertCoin(new Coin(QUARTER));
        assertEquals("$0.40", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void returnMultipleInvalidCoinsAndDisplayAmountOfValid(){
        vendingMachine.insertCoin(new Coin(PENNY));
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.insertCoin(new Coin(NICKEL));
        vendingMachine.insertCoin(new Coin(PENNY));
        vendingMachine.insertCoin(new Coin(0.1, 1.5));
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.insertCoin(new Coin(NICKEL));
        String finalAmount = vendingMachine.getDisplay();
        assertEquals(3, vendingMachine.getReturnedCoins().size());
        assertEquals("$0.40", finalAmount);
        vendingMachine.clear();
    }

    @Test
    public void clearInputMethodTest(){
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.clear();
        vendingMachine.insertCoin(new Coin(NICKEL));
        assertEquals("$0.05", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void clearRejectedCoinsTest(){
        vendingMachine.insertCoin(new Coin(PENNY));
        vendingMachine.clear();
        assertEquals(0, vendingMachine.getReturnedCoins().size());
    }

    //Feature Select Product Testing
    @Test
    public void selectProductTest(){
        vendingMachine.selectProduct(COLA);
        assertEquals(COLA, vendingMachine.getSelectedProduct());
    }

    @Test
    public void selectColaProductWithNoMoneyTest(){
        vendingMachine.selectProduct(COLA);
        assertEquals("PRICE $1.00", vendingMachine.getDisplay());
    }

    @Test
    public void selectChipsProductWithNoMoneyTest(){
        vendingMachine.selectProduct(CHIPS);
        assertEquals("PRICE $0.50", vendingMachine.getDisplay());
    }

    @Test
    public void selectCandyProductWithNoMoneyTest(){
        vendingMachine.selectProduct(CANDY);
        assertEquals("PRICE $0.65", vendingMachine.getDisplay());
    }

    @Test
    public void selectChipsWithMoneyTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.selectProduct(CHIPS);
        assertEquals(THANK_YOU, vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void selectCandyProductWithNoMoneyAndCheckDisplayTwiceTest(){
        vendingMachine.selectProduct(COLA);
        assertEquals("PRICE $1.00", vendingMachine.getDisplay());
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
    }

    @Test
    public void selectColaWithSufficientMoneyAndCheckDisplayTwiceTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.selectProduct(COLA);
        assertEquals(THANK_YOU, vendingMachine.getDisplay());
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    @Test
    public void selectChipsWithSufficientMoneyAndCheckDisplayTwiceAndAmountTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.selectProduct(CHIPS);
        assertEquals(THANK_YOU, vendingMachine.getDisplay());
        assertEquals(INSERT_COIN, vendingMachine.getDisplay());
        assertEquals(BigDecimal.ZERO, vendingMachine.getCurrentAmount());
        vendingMachine.clear();
    }

    @Test
    public void selectChipsWithoutSufficientMoneyAndCheckDisplayTwiceAndAmountTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.selectProduct(COLA);
        assertEquals(PRICE + "$1.00", vendingMachine.getDisplay());
        assertEquals("$0.50", vendingMachine.getDisplay());
        vendingMachine.clear();
    }

    //Feature Make Change Testing
    @Test
    public void selectChipsWithMoreThanEnoughMoneyTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.selectProduct(CHIPS);
        assertEquals(1, vendingMachine.getReturnedCoins().size());
        assertEquals(QUARTER_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
        vendingMachine.clear();
    }

    @Test
    public void selectCandyWith75CentsTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.selectProduct(CANDY);
        assertEquals(1, vendingMachine.getReturnedCoins().size());
        assertEquals(DIME_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
        vendingMachine.clear();
    }

    @Test
    public void selectCandyWith95CentsTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.selectProduct(CANDY);
        assertEquals(2, vendingMachine.getReturnedCoins().size());
        assertEquals(QUARTER_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
        assertEquals(NICKEL_W, vendingMachine.getReturnedCoins().get(1).getCoinWeight(), 0.001);
        vendingMachine.clear();
    }

    @Test
    public void selectCandyWith85CentsToReturn2DimesTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.selectProduct(CANDY);
        assertEquals(2, vendingMachine.getReturnedCoins().size());
        assertEquals(DIME_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
        assertEquals(DIME_D, vendingMachine.getReturnedCoins().get(1).getCoinDiameter(), 0.001);
    }

    @Test
    public void selectCandyWith1Dollar55CentsAndExpectHighestChangeDenominationsTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(NICKEL));
        vendingMachine.selectProduct(CANDY);
        assertEquals(5, vendingMachine.getReturnedCoins().size());
        assertEquals(QUARTER_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
        assertEquals(QUARTER_W, vendingMachine.getReturnedCoins().get(2).getCoinWeight(), 0.001);
        assertEquals(DIME_W, vendingMachine.getReturnedCoins().get(3).getCoinWeight(), 0.001);
        assertEquals(NICKEL_W, vendingMachine.getReturnedCoins().get(4).getCoinWeight(), 0.001);
    }

    //Feature Return Coins Testing
    @Test
    public void insertANickelAndReturnItTest(){
        vendingMachine.insertCoin(new Coin(NICKEL));
        vendingMachine.returnChange();
        assertEquals(NICKEL_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
    }

    @Test
    public void insert1Dollar15CentsAndReturnItTest(){
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(QUARTER));
        vendingMachine.insertCoin(new Coin(DIME));
        vendingMachine.insertCoin(new Coin(NICKEL));
        vendingMachine.returnChange();
        assertEquals(6, vendingMachine.getReturnedCoins().size());
        assertEquals(QUARTER_W, vendingMachine.getReturnedCoins().get(0).getCoinWeight(), 0.001);
        assertEquals(QUARTER_W, vendingMachine.getReturnedCoins().get(3).getCoinWeight(), 0.001);
        assertEquals(DIME_W, vendingMachine.getReturnedCoins().get(4).getCoinWeight(), 0.001);
        assertEquals(NICKEL_W, vendingMachine.getReturnedCoins().get(5).getCoinWeight(), 0.001);
    }

    //Feature Sold Out
    @Test
    public void selectItemThatIsSoldOutTest(){
        VendingMachine vendingMachine2 = new VendingMachine(1, 0, 2);
        vendingMachine2.insertCoin(new Coin(QUARTER));
        vendingMachine2.insertCoin(new Coin(QUARTER));
        vendingMachine2.selectProduct(CHIPS);
        assertEquals(SOLD_OUT, vendingMachine2.getDisplay());

    }

    @Test
    public void selectItemAndCheckDisplayAfterSoldOutDisplayTest(){
        VendingMachine vendingMachine2 = new VendingMachine(1, 0, 2);
        vendingMachine2.insertCoin(new Coin(QUARTER));
        vendingMachine2.insertCoin(new Coin(QUARTER));
        vendingMachine2.selectProduct(CHIPS);
        assertEquals(SOLD_OUT, vendingMachine2.getDisplay());
        assertEquals("$0.50", vendingMachine2.getDisplay());
    }

    @Test
    public void selectItemAndCheckDisplayAfterSoldOutDisplayWithNoMoneyTest(){
        VendingMachine vendingMachine2 = new VendingMachine(1, 0, 2);
        vendingMachine2.selectProduct(CHIPS);
        assertEquals(SOLD_OUT, vendingMachine2.getDisplay());
        assertEquals(INSERT_COIN, vendingMachine2.getDisplay());
    }
}

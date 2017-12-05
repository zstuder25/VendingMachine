package com.zach.kata.objects;

import org.junit.Test;

import static com.zach.kata.constants.Constants.Coin.PENNY;
import static com.zach.kata.constants.Constants.Coin.PENNY_D;
import static com.zach.kata.constants.Constants.Coin.PENNY_W;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by zmans on 12/3/2017.
 */
public class CoinTest {

    @Test
    public void makeACoinWithWeightAndDiameterTest(){
        Coin coin = new Coin(0, 0);
        assertNotNull(coin);
    }

    @Test
    public void makeACoinByName(){
        Coin coin = new Coin(PENNY);
        assertEquals(PENNY_W, coin.getCoinWeight());
        assertEquals(PENNY_D, coin.getCoinDiameter());
    }
}

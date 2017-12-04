package com.zach.kata.objects;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by zmans on 12/3/2017.
 */
public class CoinTest {

    @Test
    public void makeACoinTest(){
        Coin coin = new Coin(0, 0);
        assertNotNull(coin);
    }

    @Test
    public void makeAPennyTest(){
        Coin coin = new Coin(2.5, 0.75);
        assertEquals("Penny", coin.getCoinName());
    }
}

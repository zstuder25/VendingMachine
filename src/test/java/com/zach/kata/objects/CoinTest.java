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
        Coin penny = new Coin(2.5, 0.75);
        assertEquals("Penny", penny.getCoinName());
    }

    @Test
    public void makeANickelTest(){
        Coin nickel = new Coin(5.0, 0.835);
        assertEquals("Nickel", nickel.getCoinName());
    }

    @Test
    public void makeADimeTest(){
        Coin dime = new Coin(2.268, 0.705);
        assertEquals("Dime", dime.getCoinName());
    }

    @Test
    public void makeAQuarterTest(){
        Coin quarter = new Coin(5.67, 0.955);
        assertEquals("Quarter", quarter.getCoinName());
    }

    @Test
    public void makeAnInvalidCoin(){
        Coin unknown = new Coin(10, 1);
        assertEquals("Unknown", unknown.getCoinName());
    }
}

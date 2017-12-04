package com.zach.kata.objects;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by zmans on 12/3/2017.
 */
public class CoinTest {

    @Test
    public void makeACoinTest(){
        Coin coin = new Coin(0, 0);
        assertNotNull(coin);
    }
}

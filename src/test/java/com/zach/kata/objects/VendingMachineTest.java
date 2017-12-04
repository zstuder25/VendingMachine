package com.zach.kata.objects;

import org.junit.Test;

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
}

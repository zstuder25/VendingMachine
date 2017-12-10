package com.zach.kata;


import com.zach.kata.objects.Coin;
import com.zach.kata.objects.VendingMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.zach.kata.constants.Constants.Coin.DIME;
import static com.zach.kata.constants.Constants.Coin.DIME_W;
import static com.zach.kata.constants.Constants.Coin.NICKEL;
import static com.zach.kata.constants.Constants.Coin.NICKEL_W;
import static com.zach.kata.constants.Constants.Coin.PENNY;
import static com.zach.kata.constants.Constants.Coin.QUARTER;
import static com.zach.kata.constants.Constants.Coin.QUARTER_W;
import static com.zach.kata.constants.Constants.CustomerInteraction.CHECK;
import static com.zach.kata.constants.Constants.CustomerInteraction.QUIT;
import static com.zach.kata.constants.Constants.CustomerInteraction.RETURN;
import static com.zach.kata.constants.Constants.VendingMachine.CANDY;
import static com.zach.kata.constants.Constants.VendingMachine.CHIPS;
import static com.zach.kata.constants.Constants.VendingMachine.COLA;

/**
 * Created by zmans on 12/3/2017.
 */
public class CustomerInteraction {

    private static final List<String> COINS = Collections.unmodifiableList(Arrays.asList(PENNY, NICKEL, DIME, QUARTER));
    private static final List<String> CHOICES = Collections.unmodifiableList(Arrays.asList(COLA, CHIPS, CANDY));

    private static void printReturnedCoinValueStrings(VendingMachine vendingMachine) {
        ArrayList<Coin> returnedCoins = vendingMachine.getReturnedCoins();
        System.out.println("Your Returned coins are:");
        for (Coin c : returnedCoins) {
            if (c.getCoinWeight() == NICKEL_W) {
                System.out.println(NICKEL);
            } else if (c.getCoinWeight() == DIME_W) {
                System.out.println(DIME);
            } else if (c.getCoinWeight() == QUARTER_W) {
                System.out.println(QUARTER);
            }else{
                System.out.println("Returned a penny or unknown coin");
            }
        }
    }

    private static void userText(VendingMachine vendingMachine){
        Scanner in = new Scanner(System.in);
        System.out.println("Zach's Vending Machine");
        System.out.println("This machine does not take pennies");
        System.out.println("(Type in 'quarter', 'dime', 'nickel', or 'penny' to insert)");
        System.out.println("Your Options are (type them out to select): ");
        System.out.println("Enter 'Cola'");
        System.out.println("Enter 'Chips'");
        System.out.println("Enter 'Candy'");
        System.out.println("You Can Check the display at anytime by typing 'check' ");
        System.out.println("Return your coins at any time by typing 'return'");
        System.out.println("Type in q to exit the vending machine");
        System.out.println("-----------------------------------------------------");
        System.out.println(vendingMachine.getDisplay());
        while (in.hasNext()){
            String input = in.nextLine().toUpperCase();
            if(COINS.contains(input)){
                vendingMachine.insertCoin(new Coin(input));
                System.out.println(vendingMachine.getDisplay());
            }else if(input.equals(CHECK)){
                System.out.println(vendingMachine.getDisplay());
            }else if(CHOICES.contains(input)){
                vendingMachine.selectProduct(input);
                printReturnedCoinValueStrings(vendingMachine);
                System.out.println(vendingMachine.getDisplay());
            }else if(input.equals(RETURN)){
                vendingMachine.returnChange();
                printReturnedCoinValueStrings(vendingMachine);
                System.out.println(vendingMachine.getDisplay());
            }else if(input.equals(QUIT)){
                System.exit(1);
            }else{
                System.out.println("Sorry, that's invalid input");
            }
        }

    }

    public static void main(String[] args){
        VendingMachine vendingMachine;
        if(args.length > 0){
            if(args.length == 4){
                vendingMachine = new VendingMachine(Integer.parseInt(args[1]), Integer.parseInt(args[2]),
                        Integer.parseInt(args[3]), Boolean.parseBoolean(args[0]));
            }else{
                vendingMachine = new VendingMachine(Boolean.parseBoolean(args[0]));
            }
        }else{
            vendingMachine = new VendingMachine();
        }

        userText(vendingMachine);
    }
}

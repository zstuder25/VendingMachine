package com.zach.kata.objects;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.zach.kata.constants.Constants.Coin.DIME;
import static com.zach.kata.constants.Constants.Coin.DIME_D;
import static com.zach.kata.constants.Constants.Coin.DIME_W;
import static com.zach.kata.constants.Constants.Coin.NICKEL_D;
import static com.zach.kata.constants.Constants.Coin.NICKEL_W;
import static com.zach.kata.constants.Constants.Coin.QUARTER;
import static com.zach.kata.constants.Constants.Coin.QUARTER_D;
import static com.zach.kata.constants.Constants.Coin.QUARTER_W;
import static com.zach.kata.constants.Constants.VendingMachine.INSERT_COIN;
import static com.zach.kata.constants.Constants.VendingMachine.PRICE;
import static com.zach.kata.constants.Constants.VendingMachine.THANK_YOU;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachine {

    private double currentAmount = 0;
    private static NumberFormat formatter =  NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    private ArrayList<Coin> returnedCoins = new ArrayList<Coin>();
    private String selectedProduct;
    private String display;

    public double getCurrentAmount() {
        return currentAmount;
    }

    private enum Products {
        COLA(1.00),
        CHIPS(0.50),
        CANDY(0.65);

        private final double price;

        Products(double price){
            this.price = price;
        }

        public double getPrice(){return price;}
    }

    private String convertAmount(){
        return formatter.format(currentAmount);
    }

    private String convertAmount(double amount){
        return formatter.format(amount);
    }

    public void insertCoin(Coin coin){
        if(coin.getCoinWeight() == NICKEL_W && coin.getCoinDiameter() == NICKEL_D){
            currentAmount += 0.05;
        }else if(coin.getCoinWeight() == DIME_W && coin.getCoinDiameter() == DIME_D){
            currentAmount += 0.10;
        }else if(coin.getCoinWeight() == QUARTER_W && coin.getCoinDiameter() == QUARTER_D){
            currentAmount += 0.25;
        }else{
            returnedCoins.add(coin);
        }
        if(currentAmount == 0){
            setDisplay(INSERT_COIN);
        }else{
            setDisplay(convertAmount());
        }
    }

    public ArrayList<Coin> getReturnedCoins() {
        return returnedCoins;
    }

    private void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void clear(){
        currentAmount = 0;
        returnedCoins.clear();
    }

    public void selectProduct(String product) {
        setSelectedProduct(product);
        determineDisplay(product);
    }

    public String getDisplay() {
        if(display.contains(PRICE)){
            String oldDisplay = display;
            setDisplay(currentAmount == 0.0 ? INSERT_COIN : convertAmount());
            return oldDisplay;
        }else if(display.equals(THANK_YOU)){
            String oldDisplay = display;
            setDisplay(INSERT_COIN);
            clear();
            return oldDisplay;
        }
        return display;
    }

    private void setDisplay(String display) {
        this.display = display;
    }

    private void determineDisplay(String productString){
        Products product = Products.valueOf(productString);
        if(product.getPrice() > currentAmount){
            setDisplay(PRICE + convertAmount(product.getPrice()));
        }else{
            setDisplay(THANK_YOU);
            if(product.getPrice() < currentAmount){
                double remainingAmount = currentAmount - product.getPrice();
                if(isDoubleEqual(remainingAmount, 0.25)){
                    returnedCoins.add(new Coin(QUARTER));
                }else if(isDoubleEqual(remainingAmount, 0.10)){
                    returnedCoins.add(new Coin(DIME));
                }
            }
        }

    }

    public static boolean isDoubleEqual(double d0, double d1) {
        final double delta = 0.0001;
        return d0 == d1 ? true : Math.abs(d0 - d1) < delta;
    }
}

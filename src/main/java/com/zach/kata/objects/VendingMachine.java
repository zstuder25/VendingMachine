package com.zach.kata.objects;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.zach.kata.constants.Constants.Coin.DIME_D;
import static com.zach.kata.constants.Constants.Coin.DIME_W;
import static com.zach.kata.constants.Constants.Coin.NICKEL_D;
import static com.zach.kata.constants.Constants.Coin.NICKEL_W;
import static com.zach.kata.constants.Constants.Coin.QUARTER_D;
import static com.zach.kata.constants.Constants.Coin.QUARTER_W;
import static com.zach.kata.constants.Constants.VendingMachine.INSERT_COIN;
import static com.zach.kata.constants.Constants.VendingMachine.PRICE;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachine {

    private double currentAmount = 0;
    private static NumberFormat formatter =  NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    private ArrayList<Coin> rejectedCoins = new ArrayList<Coin>();
    private String selectedProduct;
    private String display;

    private enum Products {
        COLA("Cola", 1.00),
        CHIPS("Chips", 0.50);

        private final String name;
        private final double price;

        Products(String name, double price){
            this.name = name;
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

    public String insertCoin(Coin coin){
        if(coin.getCoinWeight() == NICKEL_W && coin.getCoinDiameter() == NICKEL_D){
            currentAmount += 0.05;
            return convertAmount();
        }else if(coin.getCoinWeight() == DIME_W && coin.getCoinDiameter() == DIME_D){
            currentAmount += 0.10;
            return convertAmount();
        }else if(coin.getCoinWeight() == QUARTER_W && coin.getCoinDiameter() == QUARTER_D){
            currentAmount += 0.25;
            return convertAmount();
        }else{
            rejectedCoins.add(coin);
            if(currentAmount == 0){
                return INSERT_COIN;
            }else{
                return  convertAmount();
            }
        }
    }

    public ArrayList<Coin> getRejectedCoins() {
        return rejectedCoins;
    }

    private void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void clear(){
        currentAmount = 0;
        rejectedCoins.clear();
    }

    public String selectProduct(String product) {
        setSelectedProduct(product);
        determineDisplay(product);
        return getDisplay();
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    private void determineDisplay(String productString){
        Products product = Products.valueOf(productString);
        switch (product){
            case COLA:
                if(product.getPrice() > currentAmount){
                    setDisplay(PRICE + convertAmount(product.getPrice()));
                }
                break;
            case CHIPS:
                if(product.getPrice() > currentAmount){
                    setDisplay(PRICE + convertAmount(product.getPrice()));
                }
                break;
            default:
                setDisplay("");
        }

    }
}

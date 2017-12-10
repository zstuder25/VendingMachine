package com.zach.kata.objects;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.zach.kata.constants.Constants.Coin.DIME;
import static com.zach.kata.constants.Constants.Coin.DIME_D;
import static com.zach.kata.constants.Constants.Coin.DIME_VAL;
import static com.zach.kata.constants.Constants.Coin.DIME_W;
import static com.zach.kata.constants.Constants.Coin.NICKEL;
import static com.zach.kata.constants.Constants.Coin.NICKEL_D;
import static com.zach.kata.constants.Constants.Coin.NICKEL_VAL;
import static com.zach.kata.constants.Constants.Coin.NICKEL_W;
import static com.zach.kata.constants.Constants.Coin.QUARTER;
import static com.zach.kata.constants.Constants.Coin.QUARTER_D;
import static com.zach.kata.constants.Constants.Coin.QUARTER_VAL;
import static com.zach.kata.constants.Constants.Coin.QUARTER_W;
import static com.zach.kata.constants.Constants.VendingMachine.CANDY;
import static com.zach.kata.constants.Constants.VendingMachine.CANDY_VAL;
import static com.zach.kata.constants.Constants.VendingMachine.CHIPS;
import static com.zach.kata.constants.Constants.VendingMachine.CHIPS_VAL;
import static com.zach.kata.constants.Constants.VendingMachine.COLA;
import static com.zach.kata.constants.Constants.VendingMachine.COLA_VAL;
import static com.zach.kata.constants.Constants.VendingMachine.EXACT_CHANGE;
import static com.zach.kata.constants.Constants.VendingMachine.INSERT_COIN;
import static com.zach.kata.constants.Constants.VendingMachine.PRICE;
import static com.zach.kata.constants.Constants.VendingMachine.SOLD_OUT;
import static com.zach.kata.constants.Constants.VendingMachine.THANK_YOU;

/**
 * Created by zmans on 12/3/2017.
 */
public class VendingMachine {

    public VendingMachine(){
        this(3, 3, 3);
        defaultDisplay = INSERT_COIN;
        setDisplay(defaultDisplay);
    }
    public VendingMachine(boolean exactChange){
        this(3, 3, 3, exactChange);
    }
    public VendingMachine(int colaStock, int chipsStock, int candyStock, boolean exactChange){
        this(colaStock, chipsStock, candyStock);
        if(exactChange){
            defaultDisplay = EXACT_CHANGE;
            setDisplay(defaultDisplay);
        }else{
            defaultDisplay = INSERT_COIN;
            setDisplay(defaultDisplay);
        }
    }
    private VendingMachine(int colaStock, int chipsStock, int candyStock){
        stock = new HashMap<String, Integer>();
        stock.put(COLA, colaStock);
        stock.put(CHIPS, chipsStock);
        stock.put(CANDY, candyStock);
    }

    private BigDecimal currentAmount = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_CEILING);
    private static NumberFormat formatter =  NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    private ArrayList<Coin> returnedCoins = new ArrayList<Coin>();
    private String selectedProduct;
    private String display;
    private Map<String, Integer> stock;
    private String defaultDisplay;

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    private enum Products {
        COLA(new BigDecimal(COLA_VAL)),
        CHIPS(new BigDecimal(CHIPS_VAL)),
        CANDY(new BigDecimal(CANDY_VAL));

        private final BigDecimal price;

        Products(BigDecimal price){
            this.price = price;
        }

        public BigDecimal getPrice(){return price;}
    }

    private String convertAmount(){
        return formatter.format(currentAmount.doubleValue());
    }

    private String convertAmount(BigDecimal amount){
        return formatter.format(amount.doubleValue());
    }

    public void insertCoin(Coin coin){
        if(coin.getCoinWeight() == NICKEL_W && coin.getCoinDiameter() == NICKEL_D){
            currentAmount = currentAmount.add(new BigDecimal(NICKEL_VAL));
        }else if(coin.getCoinWeight() == DIME_W && coin.getCoinDiameter() == DIME_D){
            currentAmount = currentAmount.add(new BigDecimal(DIME_VAL));
        }else if(coin.getCoinWeight() == QUARTER_W && coin.getCoinDiameter() == QUARTER_D){
            currentAmount = currentAmount.add(new BigDecimal(QUARTER_VAL));
        }else{
            returnedCoins.add(coin);
        }
        if(currentAmount.compareTo(BigDecimal.ZERO) == 0){
            setDisplay(defaultDisplay);
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

    private void setDisplay(String display) {
        this.display = display;
    }

    public void clear(){
        currentAmount = BigDecimal.ZERO;
        returnedCoins.clear();
    }

    public void selectProduct(String product) {
        setSelectedProduct(product);
        determineDisplay(product);
    }

    public String getDisplay() {
        if(display.contains(PRICE) || display.contains(SOLD_OUT)){
            String oldDisplay = display;
            setDisplay(currentAmount.compareTo(BigDecimal.ZERO) == 0 ? defaultDisplay : convertAmount());
            return oldDisplay;
        }else if(display.equals(THANK_YOU)){
            String oldDisplay = display;
            setDisplay(defaultDisplay);
            clear();
            return oldDisplay;
        }
        return display;
    }

    public void returnChange(){
        while(currentAmount.compareTo(BigDecimal.ZERO) > 0){
            if(currentAmount.compareTo(new BigDecimal(QUARTER_VAL)) >= 0){
                returnedCoins.add(new Coin(QUARTER));
                currentAmount = currentAmount.subtract(new BigDecimal(QUARTER_VAL));
            }else if(currentAmount.compareTo(new BigDecimal(DIME_VAL)) >= 0){
                returnedCoins.add(new Coin(DIME));
                currentAmount = currentAmount.subtract(new BigDecimal(DIME_VAL));
            }else if(currentAmount.compareTo(new BigDecimal(NICKEL_VAL)) >= 0){
                returnedCoins.add(new Coin(NICKEL));
                currentAmount = currentAmount.subtract(new BigDecimal(NICKEL_VAL));
            }
        }
        setDisplay(defaultDisplay);
    }

    private void determineDisplay(String productString){
        Products product = Products.valueOf(productString);
        if(stock.get(productString) == 0){
            setDisplay(SOLD_OUT);
        }else if(currentAmount.compareTo(product.getPrice()) < 0){
            setDisplay(PRICE + convertAmount(product.getPrice()));
        }else{
            //Make change
            if(currentAmount.compareTo(product.getPrice()) > 0){
                currentAmount = currentAmount.subtract(product.getPrice());
                returnChange();
            }
            setDisplay(THANK_YOU);
        }

    }
}

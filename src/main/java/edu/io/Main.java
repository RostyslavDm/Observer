package edu.io;

public class Main {
    public static void main(String[] args) {
        PriceFeed priceFeed = new PriceFeed();
        PriceLog priceLog = new PriceLog();
        priceFeed.publisher.subscribe(priceLog);
        Statistics statistics = new Statistics();
        priceFeed.publisher.subscribe(statistics);
    }
}

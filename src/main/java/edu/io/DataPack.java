package edu.io;

public class DataPack {
    private double price;
    private long timestamp;

    public DataPack(double price, long timestamp) {
        this.price = price;
        this.timestamp = timestamp;
    }

    public double price() {
        return price;
    }

    public long timestamp() {
        return timestamp;
    }
}

package edu.io;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PriceLog implements Subscriber<DataPack> {
    @Override
    public void update(DataPack data) {
        try (FileWriter fw = new FileWriter("price.log", true)) {
            fw.write("Price: " + data.price() + ", Time: " + getFormattedTimestamp(data.timestamp()) + "\n");
            System.out.println("Price: "+data.price()+", Time: "+getFormattedTimestamp(data.timestamp())+" logged to price.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFormattedTimestamp(long timestamp) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp * 1000));
    }
}
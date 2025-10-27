package edu.io;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HttpDataSource implements DataSource {
    private static final String API_URL = "https://www.goldapi.io/api/XPT/USD";
    private static final String API_KEY = "goldapi-2l8qahdsmh9eu8tp-io";        

    @Override
    public DataPack getData() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("x-access-token", API_KEY);

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                String json = scanner.useDelimiter("\\A").next();
                return parsePriceFromJson(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error fetching data from API.");
            return new DataPack(0, System.currentTimeMillis());
        }
    }

    private DataPack parsePriceFromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PriceResponse priceResponse = objectMapper.readValue(json, PriceResponse.class);
            return new DataPack(priceResponse.getPrice(), priceResponse.getTimestamp());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error parsing JSON response.");
            return new DataPack(0, System.currentTimeMillis());
        }
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class PriceResponse {
        private long timestamp;
        private double price;

        public long getTimestamp() {
            return timestamp;
        }

        public double getPrice() {
            return price;
        }
    }
}

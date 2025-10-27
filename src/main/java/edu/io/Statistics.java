package edu.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Statistics implements Subscriber<DataPack> {
    private List<Double> prices = new ArrayList<>();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Statistics() {
        startStatisticsTask();
        listenForEnterKey();
    }

    @Override
    public void update(DataPack data) {
        prices.add(data.price());
    }

    private void startStatisticsTask() {
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Current price: " + prices.get(prices.size() - 1));
        }, 10, 10, TimeUnit.SECONDS);
    }

    private void listenForEnterKey() {
        new Thread(() -> {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    scanner.nextLine();
                    displayStatistics();
                }
            }
        }).start();
    }

    private void displayStatistics() {
        if (!prices.isEmpty()) {
            double average = prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            double max = prices.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
            double min = prices.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
            System.out.println("Avarage price: " + average);
            System.out.println("Max price: " + max);
            System.out.println("Min price: " + min);
        }
    }

    public double getAverage() {
        return prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double getMax() {
        return prices.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
    }

    public double getMin() {
        return prices.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
    }
}


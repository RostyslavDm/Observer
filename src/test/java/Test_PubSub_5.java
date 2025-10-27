import edu.io.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class Test_PubSub_5 {
    private PriceFeed priceFeed;
    private Statistics statistics;

    @BeforeEach
    void setUp() {
        priceFeed = new PriceFeed(new DataSource() {
            int count = 0;
            List<DataPack> mockData = List.of(
                new DataPack(1000, 0),
                new DataPack(995, 1),
                new DataPack(1010, 2)
            );

            @Override
            public DataPack getData() {
                return mockData.get(count++ % mockData.size());
            }
        });
        statistics = new Statistics();
        priceFeed.publisher.subscribe(statistics);
    }

    @Test
    void testAveragePrice() throws InterruptedException {
        Thread.sleep(11000);
        Assertions.assertEquals(1001.67, statistics.getAverage(), 0.01);
    }

    @Test
    void testMaxPrice() throws InterruptedException {
        Thread.sleep(11000);
        Assertions.assertEquals(1010, statistics.getMax(), 0);
    }

    @Test
    void testMinPrice() throws InterruptedException {
        Thread.sleep(11000);
        Assertions.assertEquals(995, statistics.getMin(), 0);
    }
}
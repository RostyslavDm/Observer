import edu.io.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class Test_PubSub_4 {

    PriceFeed priceFeed;

    @Test
    void can_parametrize_data_type_in_publisher() {
        Assertions.assertTrue(0 < Publisher.class.getTypeParameters().length);
    }

    @Test
    void can_parametrize_data_type_in_subscriber() {
        Assertions.assertTrue(0 < Subscriber.class.getTypeParameters().length);
    }

    @Test
    void can_inject_DataSource_to_PriceFeed() {
        _create_PriceFeed_mock();
        priceFeed.publisher.subscribe(data -> {
            Assertions.assertEquals(1000, data.price());
        });
        priceFeed.refresh();
        priceFeed.publisher.subscribe(data -> {
            Assertions.assertEquals(999, data.price());
        });
    }

    @Test
    void HttpDataSource_get_real_data() {
        priceFeed =  new PriceFeed(new HttpDataSource());
        priceFeed.publisher.subscribe(data -> {
            System.out.println(data);
            Assertions.assertNotNull(data);
            Assertions.assertTrue(data.price() > 0);
            Assertions.assertTrue(
                    data.timestamp() <= System.currentTimeMillis() / 1000);
        });
        priceFeed.refresh();
    }


    // -----------------------
    void _create_PriceFeed_mock() {
        priceFeed = new PriceFeed(new DataSource() {
            int i = 0;
            List<DataPack> prices = List.of(
                    new DataPack(1000, 0),
                    new DataPack(999, 1),
                    new DataPack(1001, 2));
            @Override
            public DataPack getData() {
                var d = prices.get(i);
                i = (i + 1) % prices.size();
                return d;
            }
        });
    }

}

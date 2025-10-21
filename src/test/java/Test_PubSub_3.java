import edu.io.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class Test_PubSub_3 {
/*
    Publisher publisher;
    Subscriber testSubscriber;
    Object t;

    @BeforeEach
    void setUp() {
        publisher = new Publisher();
        testSubscriber = new Subscriber() {
            @Override
            public void update(Object data) {
                t = data;
            }
        };
    }

    @Test
    void subscriber_get_update() {
        publisher.subscribe(testSubscriber);
        publisher.publish(13);
        Assertions.assertEquals(13, (Integer)t);
    }

    @Test
    void can_unsubscribe() {
        publisher.subscribe(testSubscriber);
        publisher.unsubscribe(testSubscriber);
        t = 7;
        publisher.publish(13);
        Assertions.assertEquals(7, (Integer)t);
    }

    @Test
    void PriceFeed_has_publisher() {
        PriceFeed pf = new PriceFeed();
        Assertions.assertInstanceOf(Publisher.class, pf.publisher);
    }

    @Test
    void PriceLog_is_subscriber() {
        Assertions.assertInstanceOf(Subscriber.class, new PriceLog());
    }

    @Test
    void PriceLog_creates_log_file() {
        var pl = new PriceLog();
        pl.update(new DataPack(3.14, 0));
        Assertions.assertTrue(new File("price.log").exists());
    }
*/
}

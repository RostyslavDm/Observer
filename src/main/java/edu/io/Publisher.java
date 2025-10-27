package edu.io;

import java.util.ArrayList;
import java.util.List;

public class Publisher<T> {
    private List<Subscriber<T>> subscribers = new ArrayList<>();

    public void subscribe(Subscriber<T> sub) {
        subscribers.add(sub);
    }

    public void unsubscribe(Subscriber<T> sub) {
        subscribers.remove(sub);
    }

    public void publish(T data) {
        for (Subscriber<T> sub : subscribers) {
            sub.update(data);
        }
    }
}

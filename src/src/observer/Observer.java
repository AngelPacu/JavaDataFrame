package observer;

import java.util.ArrayList;
import java.util.HashMap;

public class Observer{
    private final HashMap<String, ArrayList<String>> clients;

    public Observer() {
        clients = new HashMap<>();
    }

    public void subscribe(String event, String subscriber) {
        if (!clients.get(event).contains(subscriber)) clients.get(event).add(subscriber);
    }

    public void unsubscribe(String event, String subscriber) {
        clients.get(event).remove(subscriber);
    }

    public void notifySubscribers(String event) {
        if (clients.containsKey(event)){
            clients.get(event).forEach(x->System.out.println("Notified "+x+" for event "+ event));
        }
        else System.out.println("event not found");
    }
}

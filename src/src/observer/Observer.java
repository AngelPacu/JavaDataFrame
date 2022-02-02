package observer;

import java.util.ArrayList;
import java.util.HashMap;

public class Observer{
    private final HashMap<String, ArrayList<Client>> clients;

    public Observer() {
        clients = new HashMap<>();
        clients.put("any", new ArrayList<>()); // logObserver
        clients.put("query", new ArrayList<>()); // queryObserver
    }

    public void subscribe(String event, Client subscriber) {
        if (!clients.get(event).contains(subscriber)) clients.get(event).add(subscriber);
    }

    public void unsubscribe(String event, Client subscriber) {
        clients.get(event).remove(subscriber);
    }

    public void notifySubscribers(String event) {
        if (clients.containsKey(event)){
            clients.get(event).forEach(client->client.receiveNotification(event));
        }
        else System.out.println("event not found");
    }
}

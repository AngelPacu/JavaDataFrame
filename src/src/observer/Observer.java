package observer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Observer design pattern that will allow us to control the execution of events by classes/clients.
 */
public class Observer{
    private final HashMap<String, ArrayList<Client>> clients; // HashMap(Key = Events, Values = ArrayList de clients

    public Observer() {
        clients = new HashMap<>();
        clients.put("any", new ArrayList<>()); // logObserver
        clients.put("query", new ArrayList<>()); // queryObserver
    }

    /**
     * Function to add a client to the list and assign it to an event.
     * @param event:
     * @param subscriber: Client
     */
    public void subscribe(String event, Client subscriber) {
        if (!clients.get(event).contains(subscriber)) clients.get(event).add(subscriber);
    }

    /**
     * Function to remove a client to the list and assign it to an event.
     * @param event
     * @param subscriber: Client
     */
    public void unsubscribe(String event, Client subscriber) {
        clients.get(event).remove(subscriber);
    }

    /**
     * Execute the function of "receiveNotification" of all clients assigned to an event,
     * If it does not find the event, it will display a not found message.
     * @param event
     */
    public void notifySubscribers(String event) {
        if (clients.containsKey(event)){
            clients.get(event).forEach(client->client.receiveNotification(event));
        }
        else System.out.println("event not found");
    }
}

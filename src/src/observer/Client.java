package observer;

/**
 * An object that will only have a string and will be able to execute a function that will notify the executed event.
 */
public class Client {
    String name;

    /**
     * Constructor
     * @param name: Simple string.
     */
    public Client(String name) {
        this.name = name;
    }
    /**
     * It will print the name of the client and the executed function.
     * @param event: Function
     */
    public void receiveNotification(String event){
        System.out.println("Notified "+name+" for event "+ event);
    }
}

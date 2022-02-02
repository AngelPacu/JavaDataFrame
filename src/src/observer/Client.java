package observer;

public class Client {
    String name;

    public Client(String name) {
        this.name = name;
    }
    public void receiveNotification(String event){
        System.out.println("Notified "+name+" for event "+ event);
    }
}

import dataFrames.DataFrame;
import dataFrames.Directory;
import dataFrames.FileDF;
import factories.AbstractFactory;
import factories.DataFrameFactory;
import observer.Client;
import observer.Interceptor;
import dataFrames.MapReduce;
import observer.Observer;
import org.junit.Before;
import org.junit.Test;
import visitor.*;

import java.io.File;
import java.util.*;


public class Main {

    ArrayList<DataFrame> dataFrames;

    @Before
    public void readFile(){
        dataFrames = new ArrayList<>();
        File[] input = new File[]{
                new File("dataFiles/cities.csv"),
                new File("dataFiles/cities.json"),
                new File("dataFiles/sales.txt"),
        };
        for (File f: input) {
            DataFrameFactory factory = AbstractFactory.create(f);
            try {
                dataFrames.add(factory.frame(f));
            } catch (Exception e) {
                System.out.println("Error en la creación:"+e);
            }
        }
    }

    @Test
    public void part1(){
        System.out.println("****** TEST 1 ******\n");
        System.out.println(dataFrames.get(0));
        System.out.println("Size: "+dataFrames.get(0).size());
        System.out.println("Columns: "+dataFrames.get(0).columns());
        System.out.println(dataFrames.get(0).at(23,"F"));
        System.out.println(dataFrames.get(0).iat(23,5));
        System.out.println(dataFrames.get(0).sort("LatD", (x, y) -> Long.compare((Long) x, (Long) y)));
        System.out.println(dataFrames.get(0).sort("State", (x, y) -> ((String) x).compareTo((String)y)));
        System.out.println(dataFrames.get(0).query("LatD",(x) -> (Long)x>48));
        System.out.println(dataFrames.get(0).extendedQuery("LatD",(x) -> (Long)x>48));
    }

    @Test
    public void part2() {
        System.out.println("****** TEST 2 ******\n");
        System.out.println(dataFrames.get(1));
        Directory testDirectory= new Directory("Arrel");
        testDirectory.addDataFrame(dataFrames.get(1));
        testDirectory.addDataFrame(new FileDF(dataFrames.get(1).extendedQuery("LatD", (x) -> (Long)x>48), dataFrames.get(1).getCategories()));
        System.out.println(testDirectory);
        //Map<String, List<Object>> prueba = testDirectory.getData();
        //System.out.println(testDirectory.extendedQuery("LatD",(x) -> (Long)x>48));
        System.out.println(testDirectory.getCategories());
        System.out.println("Respuesta");
        System.out.println(testDirectory.iat(127,5));
        System.out.println(testDirectory.at(128,"State"));
        testDirectory.removeDataFrame(dataFrames.get(1));
    }

    @Test
    public void part3() {
        System.out.println("****** TEST 3 ******\n");
        Directory testDirectory= new Directory("Arrel");
        testDirectory.addDataFrame(dataFrames.get(1));
        testDirectory.addDataFrame(new FileDF(dataFrames.get(1).extendedQuery("LatD", (x) -> (Long)x>48), dataFrames.get(1).getCategories()));
        System.out.println(testDirectory);

        System.out.println("Parallel At: "+MapReduce.parallelAt(testDirectory.getChildren(), 1, "LatD"));
        System.out.println("Column sum: "+MapReduce.addColumns(testDirectory.getChildren()));
        System.out.println("Filter + Sort: "+MapReduce.filterSort(testDirectory.getChildren(), "LatS", (x, y) -> Long.compare((Long) x, (Long) y), (x) -> (Long)x>30));
    }


    @Test
    public void part4(){
        System.out.println("****** TEST 4 ******\n");
        System.out.println(dataFrames.get(2));
        Directory testDirectory= new Directory("Arrel");
        testDirectory.addDataFrame(dataFrames.get(2));
        testDirectory.addDataFrame(new FileDF(dataFrames.get(2).extendedQuery("OrderID", (x) -> (Long)x>341417157), dataFrames.get(2).getCategories()));

        System.out.println(testDirectory.getData());
        System.out.println(testDirectory.sort("OrderID", Comparator.comparingLong(x -> (Long) x)));
        System.out.println(testDirectory.getCategories());
        System.out.println(testDirectory+"\n");
        System.out.println("Total Columns: "+MapReduce.addColumns(Arrays.asList(testDirectory,dataFrames.get(2),dataFrames.get(2),dataFrames.get(2))));
    }

    @Test
    public void part5(){
        System.out.println("****** TEST 5 ******\n");
        Visitor[] visitors = new Visitor[] {new VisitorSum(),new VisitorMax(), new VisitorMin(), new VisitorAverage()};
        for (Visitor v :visitors)
            System.out.println(dataFrames.get(1).accept(v,"LatD"));
        System.out.println(dataFrames.get(1));
    }


    @Test
    public void part6() {
        System.out.println("****** TEST 6 ******\n");
        Client[] clients = new Client[6];
        for(int i = 0; i < clients.length; ++i) {
            clients[i] = new Client("Client " + i);
        }

        Observer observer = new Observer();
        Interceptor proxy = new Interceptor(this.dataFrames.get(0), observer);
        observer.subscribe("any", clients[0]);
        observer.subscribe("any", clients[1]);
        observer.subscribe("any", clients[2]);
        observer.subscribe("query", clients[0]);
        observer.subscribe("query", clients[3]);
        observer.subscribe("query", clients[4]);
        observer.notifySubscribers("any");
        System.out.println(proxy.size() + "\n");
        System.out.println(proxy.query("City", (x) -> {
            return ((String)x).contains("a");
        }) + "\n");
        observer.unsubscribe("any", clients[0]);
        System.out.println(proxy.iat(0, 0) + "\n");
    }

    @Test
    public void part7() {
        System.out.println("****** TEST 7 ******\n");
        System.out.println(dataFrames.get(2));
        System.out.println("Size: " + dataFrames.get(2).size());
        System.out.println("Columns: " + dataFrames.get(2).columns());
        System.out.println(dataFrames.get(2).at(23, "Region"));
        System.out.println(dataFrames.get(2).at(23, "ItemType"));
        System.out.println(dataFrames.get(2).iat(23, 5));
        System.out.println(dataFrames.get(2).sort("OrderID", Comparator.comparingLong(x -> (Long) x)));
        System.out.println(dataFrames.get(2).sort("Region", Comparator.comparing(x -> ((String) x))));
        System.out.println(dataFrames.get(2).query("SalesChannel", (x) -> x.equals("Online")));
        System.out.println((dataFrames.get(2)).extendedQuery("OrderDate", (x) -> ((String)x).contains("/2015")));
    }
}

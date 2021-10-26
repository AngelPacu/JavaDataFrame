package testPart1;

import dataFrames.DataFrame;
import factories.AbstractFactory;
import factories.DataFrameFactory;

import java.io.File;


public class Main {
    public static void main(String[] args) {

        File input = new File("C:\\Users\\User\\Desktop\\Uni\\3er 1\\TAP\\FitxersProva\\DimenLookupAge8277.csv");
        DataFrameFactory factory = AbstractFactory.create(input);
        DataFrame data = factory.frame(input);

    }
}

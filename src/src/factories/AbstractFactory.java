package factories;

import factories.DataFrameFactory;

import java.io.File;

public abstract class AbstractFactory {

    public static DataFrameFactory create(File input) {
        String fileName = input.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".")+1);
        return switch (extension) {
            case ("txt") -> new TxtDFrameFactory();
            case ("csv") -> new CsvDFrameFactory();
            case ("json") -> new JsonDFrameFactory();
            default -> null;
        };
    }
}

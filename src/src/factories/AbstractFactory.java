package factories;

import java.io.File;

public abstract class AbstractFactory {

    public static DataFrameFactory create(File input) {
        String fileName = input.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".")+1);
        return switch (extension) {
            case ("txt") -> new TxtDFFactory();
            case ("csv") -> new CsvDFFactory();
            case ("json") -> new JsonDFFactory();
            default -> null;
        };
    }
}

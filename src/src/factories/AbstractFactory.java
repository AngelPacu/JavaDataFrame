package factories;

import java.io.File;

/**
 * It will indicate the format of the file to be processed.
 */
public abstract class AbstractFactory {

    /**
     * It will create the corresponding DataFrame, according to the format of the file we have read.
     * @param input: Name of the file with which we will obtain the extension.
     * @return Returns the invocation of the creation of a DataFrame.
     */
    public static DataFrameFactory create(File input) {
        String fileName = input.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".")+1);
        return switch (extension) {
            case ("txt") -> new TxtDFFactory();
            case ("csv") -> new TxtDFFactory();
            case ("json") -> new JsonDFFactory();
            default -> null;
        };
    }
}

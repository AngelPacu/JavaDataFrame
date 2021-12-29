package factories;

import java.io.File;

/**
 * Nos indicará el formato del fichero tratar.
 */
public abstract class AbstractFactory {

    /**
     * Creará el DataFrame correspondiente, según el formato del fichero que hemos leído.
     * @param input: Nombre del fichero con el que obtendremos la extensión.
     * @return Devuelve la invocación de la creación de un DataFrame.
     */
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

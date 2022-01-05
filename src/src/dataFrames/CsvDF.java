package dataFrames;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Estructura de datos leídos a partir de "CSV".
 */
public class CsvDF extends AbstractDF{
    /**
     * Constructor del CSVDataFrame.
     * @param mapList: Hashmap que contiene {Columnas,ListaDeValores}.
     * @param cat: Lista que contiene los nombres ordenados de las categorías.
     */
    public CsvDF(Map<String, List<Object>> mapList, List<String> cat) {
        data=mapList;
        categories= new ArrayList<>(cat);
    }
}

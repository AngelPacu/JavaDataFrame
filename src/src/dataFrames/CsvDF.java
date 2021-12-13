package dataFrames;

import java.util.*;

/**
 * Estructura de datos leídos a partir de "CSV".
 */
public class CsvDF extends AbstractDF{
    Map<String, List<Object>> data;
    ArrayList<String> categories;

    /**
     * Constructor del CSVDataFrame.
     * @param mapList: Hashmap que contiene <Columna,ListaDeValores>
     * @param cat: Lista que contiene los nombres ordenados de las categorias.
     */
    public CsvDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data=mapList;
        categories=cat;
    }


}

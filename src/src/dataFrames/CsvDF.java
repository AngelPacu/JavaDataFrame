package dataFrames;

import java.util.*;

/**
 * Estructura de datos leídos a partir de "CSV".
 */
public class CsvDF extends AbstractDF{
    /**
     * Constructor del CSVDataFrame.
     * @param mapList: Hashmap que contiene {Columnas,ListaDeValores}.
     * @param cat: Lista que contiene los nombres ordenados de las categorías.
     */
    public CsvDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data=mapList;
        categories=cat;
    }


}

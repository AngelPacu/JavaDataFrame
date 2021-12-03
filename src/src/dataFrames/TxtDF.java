package dataFrames;

import java.util.*;

/**
 * Estructura de datos leídos a partir de "TXT".
 */
public class TxtDF extends AbstractDF{
    Map<String, List<Object>> data;
    ArrayList<String> categories;

    /**
     * Constructor del TXTDataFrame.
     * @param mapList: Hashmap que contiene <Columna,ListaDeValores>
     * @param cat: Lista que contiene los nombres ordenados de las categorias.
     */
    public TxtDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data=mapList;
        categories=cat;
    }

}

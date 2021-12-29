package dataFrames;

import java.util.*;

/**
 * Estructura de datos leídos a partir de "TXT".
 */
public class TxtDF extends AbstractDF{
    /**
     * Constructor del TXTDataFrame.
     * @param mapList: Hashmap que contiene {Columnas,ListaDeValores}.
     * @param cat: Lista que contiene los nombres ordenados de las categorías.
     */
    public TxtDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data=mapList;
        categories=cat;
    }

}

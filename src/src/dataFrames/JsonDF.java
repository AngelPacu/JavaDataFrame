package dataFrames;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Estructura de datos leídos a partir de "JSON".
 */

public class JsonDF extends AbstractDF{
    /**
     * Constructor del JsonDataFrame.
     * @param mapList: Hashmap que contiene <Columna,ListaDeValores>
     * @param cat: Lista que contiene los nombres ordenados de las categorias.
     */
    public JsonDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data = mapList;
        categories = cat;
    }

}

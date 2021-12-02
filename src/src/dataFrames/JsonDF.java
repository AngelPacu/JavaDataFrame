package dataFrames;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonDF extends AbstractDF{
    /**
     * JsonDF: Objeto que contiene los datos leidos del fichero .JSON
     * @param mapList: Hashmap que contiene <Columna,ListaDeValores>
     * @param cat: Lista que contiene los nombres ordenados de las categorias.
     */
    public JsonDF(Map<String, List<Object>> mapList, ArrayList<String> cat) {
        data = mapList;
        categories = cat;
    }
}

package dataFrames;

import java.util.Comparator;
import java.util.Iterator;

public class JsonDF implements DataFrame{

    @Override
    public int at(int row, String column) {
        return 0;
    }

    @Override
    public int iat(int row, int column) {
        return 0;
    }

    @Override
    public int columns() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int[] sort(String column, Comparator<Integer> integerComparator) {
        return new int[0];
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}

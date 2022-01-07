package extern;

import dataFrames.DataFrame;

public class Visitor {
    DataFrame dataFrame;

    public Visitor (DataFrame df){
        dataFrame=df;
    }
    public int maximum (/*DataFrame df,*/String column){
        return dataFrame.getData().get(column).stream().mapToInt(x->(Integer) x).max().orElse(0);
    }
    public int minimum (String column){
        return dataFrame.getData().get(column).stream().mapToInt(x->(Integer) x).min().orElse(0);
    }
    public int average (String column){
        return (int) dataFrame.getData().get(column).stream().mapToInt(x->(Integer) x).average().orElse(0);
    }
    public int sum (String column){
        return dataFrame.getData().get(column).stream().mapToInt(x->(Integer) x).sum();
    }
}

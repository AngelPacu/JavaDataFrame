package factories;

import dataFrames.DataFrame;

import java.io.File;

public interface DataFrameFactory {
    DataFrame frame(File input);
}

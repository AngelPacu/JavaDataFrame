package factories;

import dataFrames.DataFrame;

import java.io.File;
import java.io.FileNotFoundException;

public interface DataFrameFactory {
    DataFrame frame(File input) throws FileNotFoundException;
}

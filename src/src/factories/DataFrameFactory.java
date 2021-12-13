package factories;

import dataFrames.DataFrame;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public interface DataFrameFactory {
    DataFrame frame(File input) throws IOException, ParseException;
}

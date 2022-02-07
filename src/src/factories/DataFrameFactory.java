package factories;

import dataFrames.DataFrame;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

/**
 * We will load the data read from the different factories and load it into a data structure <b>DataFrame</b>.
 */
public interface DataFrameFactory {

    /**
     * We will read the data from the received file and load it into the DataFrame structure according to the file type.
     * we have treated.
     * @param input: File to read.
     * @return Returns a DataFrame {HashMap,ArrayList} structure.
     * @throws IOException
     * @throws ParseException
     */
    DataFrame frame(File input, String... delim) throws IOException, ParseException;


}

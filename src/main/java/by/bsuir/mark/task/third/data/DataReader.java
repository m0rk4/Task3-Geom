package by.bsuir.mark.task.third.data;

import java.util.List;

public interface DataReader {
    List<String> readData(String filename) throws DataException;
}

package by.bsuir.mark.task.third.data.impl;

import by.bsuir.mark.task.third.data.DataException;
import by.bsuir.mark.task.third.data.DataReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileReaderImpl implements DataReader {
    private static final Logger LOGGER = LogManager.getLogger(FileReaderImpl.class);

    public List<String> readData(String filename) throws DataException {
        LOGGER.info("IN readData - filename: {}", filename);
        List<String> linesRead = new ArrayList<>();
        try (
                Reader reader = new FileReader(filename);
                BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            String lineRead = bufferedReader.readLine();
            while (lineRead != null) {
                linesRead.add(lineRead);
                lineRead = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
        LOGGER.info("IN readData - filename: {} processed", filename);
        return linesRead;
    }
}

package by.bsuir.mark.task.third.data;

import by.bsuir.mark.task.third.data.impl.FileReaderImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataReaderTest {
    private final DataReader dataReader = new FileReaderImpl();
    private static final String TEST_FILENAME_EMPTY = "./src/test/resources/test_input_1.txt";
    private static final String TEST_FILENAME_VALID = "./src/test/resources/test_input_2.txt";
    private static final String TEST_FILENAME_VALID_2 = "./src/test/resources/test_input_3.txt";
    private static final String TEST_FILENAME_INVALID = "*";

    @Test(expected = DataException.class)
    public void testReadDataShouldThrowDataExceptionWhenIncorrectFilenameProvided() throws DataException {
        dataReader.readData(TEST_FILENAME_INVALID);
    }

    @Test
    public void testReadDataShouldReturnEmptyListWhenEmptyFileProvided() throws DataException {
        // given

        // when
        List<String> result = dataReader.readData(TEST_FILENAME_EMPTY);
        // then
        Assert.assertEquals(Collections.EMPTY_LIST, result);
    }

    @Test
    public void testReadDataShouldReturnListOfStringWhenNonEmptyFileProvided() throws DataException {
        // given

        // when
        List<String> result = dataReader.readData(TEST_FILENAME_VALID);
        // then
        Assert.assertEquals(Arrays.asList("test", "line", "ends"), result);
    }

    @Test
    public void testReadDataShouldReturnListOfStringWhenFileContainingCRLFAndSpacesProvided() throws DataException {
        // given

        // when
        List<String> result = dataReader.readData(TEST_FILENAME_VALID_2);
        // then
        Assert.assertEquals(Arrays.asList("i love java", "", "yes   i am", "epam"), result);
    }
}

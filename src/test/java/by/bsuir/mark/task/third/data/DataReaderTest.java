package by.bsuir.mark.task.third.data;

import by.bsuir.mark.task.third.data.impl.FileReaderImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataReaderTest {
    private final DataReader dataReader = new FileReaderImpl();
    private final String TEST_FILENAME_1 = "./src/test/resources/test_input_1.txt";
    private final String TEST_FILENAME_2 = "./src/test/resources/test_input_2.txt";
    private final String TEST_FILENAME_3 = "./src/test/resources/test_input_3.txt";

    @Test(expected = DataException.class)
    public void testReadDataShouldThrowDataExceptionWhenIncorrectFilenameProvided() throws DataException {
        dataReader.readData(TEST_FILENAME_1 + "*");
    }

    @Test
    public void testReadDataShouldReturnEmptyListWhenEmptyFileProvided() throws DataException {
        // given

        // when
        List<String> res = dataReader.readData(TEST_FILENAME_1);
        // then
        Assert.assertEquals(Collections.EMPTY_LIST, res);
    }

    @Test
    public void testReadDataShouldReturnListOfStringWhenNonEmptyFileProvided() throws DataException {
        // given

        // when
        List<String> res = dataReader.readData(TEST_FILENAME_2);
        // then
        Assert.assertEquals(Arrays.asList("test", "line", "ends"), res);
    }

    @Test
    public void testReadDataShouldReturnListOfStringWhenFileContainingCRLFAndSpacesProvided() throws DataException {
        // given

        // when
        List<String> res = dataReader.readData(TEST_FILENAME_3);
        // then
        Assert.assertEquals(Arrays.asList("i love java", "", "yes   i am", "epam"), res);
    }
}

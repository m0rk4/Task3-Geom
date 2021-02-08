package by.bsuir.mark.task.third.creation;

import by.bsuir.mark.task.third.data.DataException;
import by.bsuir.mark.task.third.data.DataReader;
import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Polyhedron;
import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.parsing.PolyhedronDataParser;
import by.bsuir.mark.task.third.validation.PolyhedronDataValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PolyhedronCreatorTest {
    private PolyhedronCreator polyhedronCreator;

    private static final List<String> TEST_LINES = Arrays.asList(
            "1.0 23.23 12.33|323.323    32.1 3|1 23 3|9 5 3",
            "1 2   3|4 5 6|7.0 8 9|10.0 11 12"
    );

    @Before
    public void setUp() throws DataException {
        DataReader dataReader = mock(DataReader.class);
        when(dataReader.readData(anyString())).thenReturn(TEST_LINES);

        PolyhedronDataValidator polyhedronDataValidator = mock(PolyhedronDataValidator.class);
        when(polyhedronDataValidator.isValid(anyString())).thenReturn(true);

        PolyhedronDataParser polyhedronDataParser = mock(PolyhedronDataParser.class);
        when(polyhedronDataParser.parsePolyhedronData("1.0 23.23 12.33|323.323    32.1 3|1 23 3|9 5 3"))
                .thenReturn(
                        new Tetrahedron(
                                new Point3D(1.0, 23.23, 12.33),
                                new Point3D(323.323, 32.1, 3),
                                new Point3D(1, 23, 3),
                                new Point3D(9, 5, 3)
                        )
                );

        when(polyhedronDataParser.parsePolyhedronData("1 2   3|4 5 6|7.0 8 9|10.0 11 12"))
                .thenReturn(
                   new Tetrahedron(
                           new Point3D(1, 2, 3),
                           new Point3D(4, 5, 6),
                           new Point3D(7.0, 8, 9),
                           new Point3D(10.0, 11, 12)
                   )
                );

        polyhedronCreator = new PolyhedronCreator(dataReader, polyhedronDataValidator, polyhedronDataParser);
    }

    @Test
    public void testCreatePolyhedronsShouldReturnListOfPolyhedronsWhenCorrectDataProvided() throws PolyhedronCreatorException {
        // given
        String filename = "ANY_CORRECT_FILENAME";
        // when
        List<Polyhedron> polyhedrons = polyhedronCreator.createPolyhedrons(filename);
        // then
        Assert.assertEquals(Arrays.asList(
                new Tetrahedron(
                        new Point3D(1, 23, 3),
                        new Point3D(1.0, 23.23, 12.33),
                        new Point3D(323.323, 32.1, 3),
                        new Point3D(9, 5, 3)
                ),
                new Tetrahedron(
                        new Point3D(7.0, 8, 9),
                        new Point3D(1, 2, 3),
                        new Point3D(4, 5, 6),
                        new Point3D(10.0, 11, 12)
                )
        ), polyhedrons);
    }
}

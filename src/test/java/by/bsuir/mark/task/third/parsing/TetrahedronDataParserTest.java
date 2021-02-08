package by.bsuir.mark.task.third.parsing;

import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Polyhedron;
import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.parsing.impl.TetrahedronDataParserImpl;
import org.junit.Assert;
import org.junit.Test;

public class TetrahedronDataParserTest {
    private final PolyhedronDataParser tetrahedronDataParser = new TetrahedronDataParserImpl();

    @Test
    public void testParseTetrahedronDataShouldReturnTetrahedronWhenCorrectDataProvided() {
        // given
        String data = "1.0 23.23 12.33|323.323    32.1 3|1 23 3|9 5 8";
        // when
        Polyhedron result = tetrahedronDataParser.parsePolyhedronData(data);
        // then
        Tetrahedron expected = new Tetrahedron();
        Assert.assertEquals(
                new Tetrahedron(
                        new Point3D(1.0, 23.23, 12.33),
                        new Point3D(323.323, 32.1, 3),
                        new Point3D(1, 23, 3),
                        new Point3D(9, 5, 8)
                ),
                result);
    }

    @Test
    public void testParseTetrahedronDataShouldReturnTetrahedronWhenCorrectDataButPointsPositionSeemsToBeDifferent() {
        // given
        String data = "1.0 23.23 12.33|323.323    32.1 3|1 23 3|9 5 8";
        // when
        Polyhedron result = tetrahedronDataParser.parsePolyhedronData(data);
        // then
        Tetrahedron expected = new Tetrahedron();
        Assert.assertEquals(
                new Tetrahedron(
                        new Point3D(9, 5, 8),
                        new Point3D(323.323, 32.1, 3),
                        new Point3D(1, 23, 3),
                        new Point3D(1.0, 23.23, 12.33)
                ),
                result);
    }

    @Test
    public void testParseTetrahedronDataShouldReturnTetrahedronWhenCorrectDataProvided2()  {
        // given
        String data = "1 2 3.0|4    5    6.0|7 8.0 9|10  11   12";
        // when
        Polyhedron result = tetrahedronDataParser.parsePolyhedronData(data);
        // then
        Tetrahedron expected = new Tetrahedron();
        Assert.assertEquals(
                new Tetrahedron(
                        new Point3D(1.0, 2, 3),
                        new Point3D(4, 5, 6),
                        new Point3D(7, 8, 9),
                        new Point3D(10, 11, 12)
                ),
                result);
    }
}

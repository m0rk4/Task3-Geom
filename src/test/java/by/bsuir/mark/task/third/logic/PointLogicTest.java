package by.bsuir.mark.task.third.logic;

import by.bsuir.mark.task.third.entity.Octant;
import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Polyhedron;
import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.logic.impl.PointLogicImpl;
import org.junit.Assert;
import org.junit.Test;

public class PointLogicTest {
    private static final double DELTA = 0.01;
    private final PointLogic pointLogic = new PointLogicImpl();

    @Test
    public void testSubtractShouldSubtractVectorsWhenTwoVectorsProvided() {
        // given
        Point3D vectorFirst = new Point3D(1,1,1);
        Point3D vectorSecond = new Point3D(1,1,1);
        // when
        Point3D result = pointLogic.subtract(vectorFirst, vectorSecond);
        // then
        Assert.assertEquals(new Point3D(0,0,0), result);
    }


    @Test
    public void testAddShouldAddVectorsWhenTwoVectorsProvided() {
        // given
        Point3D vectorFirst = new Point3D(1,1,1);
        Point3D vectorSecond = new Point3D(1,1,1);
        // when
        Point3D result = pointLogic.add(vectorFirst, vectorSecond);
        // then
        Assert.assertEquals(new Point3D(2,2,2), result);
    }

    @Test
    public void testGetMagnitudeShouldGetMagnitudeWhenVectorProvided() {
        // given
        Point3D vector = new Point3D(1,2,2);
        // when
        double result = pointLogic.getMagnitude(vector);
        // then
        Assert.assertEquals(3, result, DELTA);
    }

    @Test
    public void testGetAngleShouldGetAngleBetweenVectorsWhenTwoVectorsProvided() {
        // given
        Point3D vectorFirst = new Point3D(1,0,0);
        Point3D vectorSecond = new Point3D(-1,0,0);
        // when
        double result = pointLogic.getAngle(vectorFirst, vectorSecond);
        // then
        Assert.assertEquals(Math.PI, result, DELTA);
    }

    @Test
    public void testArePointInOctantShouldReturnTrueWhenPolyhedronPointsInOctant() {
        // given
        Polyhedron polyhedron = new Tetrahedron(
                new Point3D(1, 1, 1),
                new Point3D(2, 2, 2),
                new Point3D(0.5, 22, 7),
                new Point3D(11, 12, 13)
        );
        Octant octant = Octant.I;
        // when
        boolean result = pointLogic.arePointInOctant(polyhedron, octant);
        // then
        Assert.assertTrue(result);
    }

}

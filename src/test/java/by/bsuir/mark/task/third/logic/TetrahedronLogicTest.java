package by.bsuir.mark.task.third.logic;

import by.bsuir.mark.task.third.entity.CoordinatePlane;
import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Polyhedron;
import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.logic.impl.TetrahedronLogic;
import org.junit.Assert;
import org.junit.Test;

public class TetrahedronLogicTest {
    private static final double DELTA = 0.01;
    private final PolyhedronLogic tetrahedronLogic = new TetrahedronLogic();

    @Test
    public void testGetPolyhedronVolumeShouldReturnTetrahedronVolumeWhenTetrahedronProvided() {
        // given
        Polyhedron polyhedron = new Tetrahedron(
                new Point3D(-0.459102, -0.0670386, 1.68168),
                new Point3D(-0.0865403, -0.122347, 0.898904),
                new Point3D(-0.436523, -0.30131, 1.92251),
                new Point3D(0, 0, 0)
        );
        // when
        double result = tetrahedronLogic.getPolyhedronVolume(polyhedron);
        // then
        Assert.assertEquals( 0.00786195, result,DELTA);
    }

    @Test
    public void testGetPolyhedronSurfaceAreaShouldReturnTetrahedronSurfaceAreaWhenTetrahedronApplied() {
        // given
        Polyhedron polyhedron = new Tetrahedron(
                new Point3D(0, 0, 0),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(0, 0, 1)
        );
        // when
        double result = tetrahedronLogic.getPolyhedronSurfaceArea(polyhedron);
        // then
        Assert.assertEquals( 2.36602541, result,DELTA);
    }

    @Test
    public void testIsFaceOnCoordinatePlaneShouldReturnTrueWhenFaceIsOnCoordinatePlaneOfTetrahedronApplied() {
        // given
        Polyhedron polyhedron = new Tetrahedron(
                new Point3D(0, 0, 0),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(0, 0, 1)
        );
        CoordinatePlane coordinatePlane = CoordinatePlane.OYZ;
        // when
        boolean result = tetrahedronLogic.isFaceOnCoordinatePlane(polyhedron, coordinatePlane);
        // then
        Assert.assertTrue(result);
    }

    @Test
    public void testIsFaceOnCoordinatePlaneShouldReturnFalseWhenFaceIsNotOnCoordinatePlaneOfTetrahedronApplied() {
        // given
        Polyhedron polyhedron = new Tetrahedron(
                new Point3D(-0.459102, -0.0670386, 1.68168),
                new Point3D(-0.0865403, -0.122347, 0.898904),
                new Point3D(-0.436523, -0.30131, 1.92251),
                new Point3D(0, 0, 0)
        );
        CoordinatePlane coordinatePlane = CoordinatePlane.OXY;
        // when
        boolean result = tetrahedronLogic.isFaceOnCoordinatePlane(polyhedron, coordinatePlane);
        // then
        Assert.assertFalse(result);
    }

    @Test
    public void testIsTetrahedronShouldReturnTrueWhenTetrahedronApplied() {
        // given
        Polyhedron polyhedron = new Tetrahedron(
                new Point3D(0, 0, 0),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(0, 0, 1)
        );
        // when
        boolean result = tetrahedronLogic.isTetrahedron(polyhedron);
        // then
        Assert.assertTrue(result);
    }

    @Test
    public void testIsTetrahedronShouldReturnFalseWhenNotTetrahedronApplied() {
        // given
        Polyhedron polyhedron = new Tetrahedron(
                new Point3D(2, 0, 0),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(0, 0, 0)
        );
        // when
        boolean result = tetrahedronLogic.isTetrahedron(polyhedron);
        // then
        Assert.assertFalse(result);
    }

    @Test
    public void testGetVolumesRatioDissectedByCoordinatePlaneShouldReturnRatioWhenTetrahedronProvided() {
        // given
        Polyhedron polyhedron = new Tetrahedron(
                new Point3D(0, 0, -0.5),
                new Point3D(1, 0, -0.5),
                new Point3D(0, 1, -0.5),
                new Point3D(0, 0, 0.5)
        );
        CoordinatePlane coordinatePlane = CoordinatePlane.OXY;
        // when
        double result = tetrahedronLogic.getVolumesRatioDissectedByCoordinatePlane(polyhedron, coordinatePlane);
        // then
        Assert.assertEquals(0.14285714285714288 , result, DELTA);
    }


}

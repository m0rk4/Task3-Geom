package by.bsuir.mark.task.third.observer;

import by.bsuir.mark.task.third.entity.Point3D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TetrahedronObserverTest {

    private static final double DELTA = 0.01;
    private final TetrahedronObserver tetrahedronObserver = TetrahedronObserver.getInstance();
    private final TetrahedronObservable tetrahedronObservableFirst = new TetrahedronObservable(1);
    private final TetrahedronObservable tetrahedronObservableSecond = new TetrahedronObservable(2);

    @Before
    public void setUp() {
        tetrahedronObservableFirst.attach(tetrahedronObserver);
        tetrahedronObservableSecond.attach(tetrahedronObserver);

        Set<Point3D> pointsSetFirst = new HashSet<>(Arrays.asList(
                new Point3D(0, 0, 0),
                new Point3D(1, 0, 0),
                new Point3D(0, 1, 0),
                new Point3D(0, 0, 1)
        ));
        tetrahedronObservableFirst.setPoints(pointsSetFirst);

        Set<Point3D> pointsSetSecond = new HashSet<>(Arrays.asList(
                new Point3D(-0.459102, -0.0670386, 1.68168),
                new Point3D(-0.0865403, -0.122347, 0.898904),
                new Point3D(-0.436523, -0.30131, 1.92251),
                new Point3D(0, 0, 0)
        ));
        tetrahedronObservableSecond.setPoints(pointsSetSecond);
    }

    @Test
    public void testGetTetrahedronSurfaceAreaShouldReturnTetrahedronNewSurfaceAreaWhenTetrahedronObservableIsChanged() {
        // given
        Integer id = tetrahedronObservableFirst.getId();
        // when
        double res = tetrahedronObserver.getTetrahedronSurfaceArea(id);
        // then
        Assert.assertEquals(2.36602541, res, DELTA);
    }

    @Test
    public void testGetTetrahedronVolumeShouldReturnTetrahedronNewVolumeWhenTetrahedronObservableIsChanged() {
        // given
        Integer id = tetrahedronObservableSecond.getId();
        // when
        double res = tetrahedronObserver.getTetrahedronVolume(id);
        // then
        Assert.assertEquals(0.00786195, res, DELTA);
    }
}

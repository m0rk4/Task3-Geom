package by.bsuir.mark.task.third.specification;

import by.bsuir.mark.task.third.entity.Octant;
import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.logic.PointLogic;
import by.bsuir.mark.task.third.logic.impl.PointLogicImpl;
import by.bsuir.mark.task.third.specification.impl.TetrahedronByPointsInOctantSpecification;
import org.junit.Assert;
import org.junit.Test;

public class TetrahedronByPointsInOctantSpecificationTest {

    private static final Octant OCTANT_TEST = Octant.I;
    private final PointLogic pointLogic = new PointLogicImpl();
    private final TetrahedronSpecification tetrahedronSpecification =
            new TetrahedronByPointsInOctantSpecification(OCTANT_TEST, pointLogic);

    @Test
    public void testIsSpecifiedShouldReturnTrueWhenAllPointsInOctant() {
        // given
        Tetrahedron tetrahedron = new Tetrahedron(
                new Point3D(1, 1, 1),
                new Point3D(2, 2, 2),
                new Point3D(0.5, 22, 7),
                new Point3D(11, 12, 13)
        );
        // when
        boolean res = tetrahedronSpecification.isSpecified(tetrahedron);
        // then
        Assert.assertTrue(res);
    }

}

package by.bsuir.mark.task.third.specification.impl;

import by.bsuir.mark.task.third.entity.Octant;
import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.logic.PointLogic;
import by.bsuir.mark.task.third.specification.TetrahedronSpecification;

public class TetrahedronByPointsInOctantSpecification implements TetrahedronSpecification {

    private final Octant octant;
    private final PointLogic pointLogic;

    public TetrahedronByPointsInOctantSpecification(Octant octant, PointLogic pointLogic) {
        this.octant = octant;
        this.pointLogic = pointLogic;
    }

    @Override
    public boolean isSpecified(Tetrahedron tetrahedron) {
        return pointLogic.arePointInOctant(tetrahedron, octant);
    }
}

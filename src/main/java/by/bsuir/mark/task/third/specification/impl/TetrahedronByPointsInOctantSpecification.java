package by.bsuir.mark.task.third.specification.impl;

import by.bsuir.mark.task.third.entity.Octant;
import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.specification.TetrahedronSpecification;

import java.util.function.Predicate;

public class TetrahedronByPointsInOctantSpecification implements TetrahedronSpecification {

    private final Octant octant;

    public TetrahedronByPointsInOctantSpecification(Octant octant) {
        this.octant = octant;
    }

    @Override
    public boolean isSpecified(Tetrahedron tetrahedron) {
        Predicate<Point3D> predicate;
        switch (octant) {
            case I:
                predicate = p -> p.getX() > 0 && p.getY() > 0 && p.getZ() > 0;
                break;
            case II:
                predicate = p -> p.getX() < 0 && p.getY() > 0 && p.getZ() > 0;
                break;
            case III:
                predicate = p -> p.getX() < 0 && p.getY() < 0 && p.getZ() > 0;
                break;
            case IV:
                predicate = p -> p.getX() > 0 && p.getY() < 0 && p.getZ() > 0;
                break;
            case V:
                predicate = p -> p.getX() > 0 && p.getY() > 0 && p.getZ() < 0;
                break;
            case VI:
                predicate = p -> p.getX() < 0 && p.getY() > 0 && p.getZ() < 0;
                break;
            case VII:
                predicate = p -> p.getX() < 0 && p.getY() < 0 && p.getZ() < 0;
                break;
            case VIII:
                predicate = p -> p.getX() > 0 && p.getY() < 0 && p.getZ() < 0;
                break;
            default:
                throw new EnumConstantNotPresentException(Octant.class, octant.name());
        }
        return arePointsSatisfyCondition(tetrahedron, predicate);
    }

    private boolean arePointsSatisfyCondition(Tetrahedron tetrahedron, Predicate<Point3D> condition) {
        for (Point3D point : tetrahedron.getPoints()) {
            if (!condition.test(point)) {
                return false;
            }
        }
        return true;
    }
}

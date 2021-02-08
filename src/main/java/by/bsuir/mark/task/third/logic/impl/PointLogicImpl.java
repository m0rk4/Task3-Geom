package by.bsuir.mark.task.third.logic.impl;

import by.bsuir.mark.task.third.entity.Octant;
import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Polyhedron;
import by.bsuir.mark.task.third.logic.PointLogic;

import java.util.function.Predicate;

public class PointLogicImpl implements PointLogic {

    @Override
    public double getAngle(Point3D vectorFirst, Point3D vectorSecond) {
        final double ax = vectorFirst.getX();
        final double ay = vectorFirst.getY();
        final double az = vectorFirst.getZ();
        final double bx = vectorSecond.getX();
        final double by = vectorSecond.getY();
        final double bz = vectorSecond.getZ();

        final double delta = (ax * bx + ay * by + az * bz) / Math.sqrt(
                (ax * ax + ay * ay + az * az) * (bx * bx + by * by + bz * bz));

        if (delta > 1.0) {
            return 0.0;
        }
        if (delta < -1.0) {
            return 180.0;
        }

        return Math.acos(delta);
    }

    @Override
    public double getMagnitude(Point3D point) {
        final double x = point.getX();
        final double y = point.getY();
        final double z = point.getZ();

        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public Point3D subtract(Point3D pointFirst, Point3D pointSecond) {
        return new Point3D(
                pointFirst.getX() - pointSecond.getX(),
                pointFirst.getY() - pointSecond.getY(),
                pointFirst.getZ() - pointSecond.getZ()
        );
    }

    @Override
    public Point3D add(Point3D pointFirst, Point3D pointSecond) {
        return new Point3D(
                pointFirst.getX() + pointSecond.getX(),
                pointFirst.getY() + pointSecond.getY(),
                pointFirst.getZ() + pointSecond.getZ()
        );
    }

    @Override
    public boolean arePointInOctant(Polyhedron polyhedron, Octant octant) {
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

        for (Point3D point : polyhedron.getPoints()) {
            if (!predicate.test(point)) {
                return false;
            }
        }
        return true;
    }
}

package by.bsuir.mark.task.third.logic.impl;

import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.logic.PointLogic;

public class PointLogicImpl implements PointLogic {

    @Override
    public double getAngle(Point3D v1, Point3D v2) {
        final double ax = v1.getX();
        final double ay = v1.getY();
        final double az = v1.getZ();
        final double bx = v2.getX();
        final double by = v2.getY();
        final double bz = v2.getZ();

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
    public Point3D subtract(Point3D p1, Point3D p2) {
        return new Point3D(
                p1.getX() - p2.getX(),
                p1.getY() - p2.getY(),
                p1.getZ() - p2.getZ()
        );
    }

    @Override
    public Point3D add(Point3D p1, Point3D p2) {
        return new Point3D(
                p1.getX() + p2.getX(),
                p1.getY() + p2.getY(),
                p1.getZ() + p2.getZ()
        );
    }
}

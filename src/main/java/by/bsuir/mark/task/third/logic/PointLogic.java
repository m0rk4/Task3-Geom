package by.bsuir.mark.task.third.logic;

import by.bsuir.mark.task.third.entity.Point3D;

public interface PointLogic {
    double getAngle(Point3D v1, Point3D v2);
    double getMagnitude(Point3D point);
    Point3D subtract(Point3D p1, Point3D p2);
    Point3D add(Point3D p1, Point3D p2);
}

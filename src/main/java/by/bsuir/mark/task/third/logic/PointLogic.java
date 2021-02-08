package by.bsuir.mark.task.third.logic;

import by.bsuir.mark.task.third.entity.Octant;
import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Polyhedron;

public interface PointLogic {
    double getAngle(Point3D vectorFirst, Point3D vectorSecond);
    double getMagnitude(Point3D point);
    Point3D subtract(Point3D pointFirst, Point3D pointSecond);
    Point3D add(Point3D pointFirst, Point3D pointSecond);
    boolean arePointInOctant(Polyhedron polyhedron, Octant octant);
}

package by.bsuir.mark.task.third.logic.impl;

import by.bsuir.mark.task.third.entity.CoordinatePlane;
import by.bsuir.mark.task.third.entity.Point3D;
import by.bsuir.mark.task.third.entity.Polyhedron;
import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.logic.PointLogic;
import by.bsuir.mark.task.third.logic.PolyhedronLogic;

import java.util.*;
import java.util.function.Function;

public class TetrahedronLogic implements PolyhedronLogic {

    private final PointLogic pointLogic = new PointLogicImpl();

    @Override
    public double getPolyhedronVolume(Polyhedron polyhedron) {
        double[][] matrix = {
                {0, 0, 0, -1},
                {0, 0, 0, 1},
                {0, 0, 0, -1},
                {0, 0, 0, 1},
        };

        int i = 0;
        Set<Point3D> points = polyhedron.getPoints();
        for (Point3D point : points) {
            matrix[i][0] = point.getX();
            matrix[i][1] = point.getY();
            matrix[i][2] = point.getZ();
            i++;
        }

        double res = 0.0;
        for (i = 0; i < matrix.length; i++) {
            double[][] extracted3x3Matrix = extract3x3Matrix(matrix, i);
            res += matrix[i][3] * calculate3x3Det(extracted3x3Matrix);
        }

        return Math.abs(res / 6.0);
    }

    private double[][] extract3x3Matrix(double[][] matrix, int rowToRemove) {
        double[][] res = new double[3][3];
        for (int i = 0, j = 0; i < matrix.length; i++) {
            if (i != rowToRemove) {
                res[j++] = matrix[i];
            }
        }
        return res;
    }

    private double calculate3x3Det(double[][] matrix) {
        return matrix[0][0] * matrix[1][1] * matrix[2][2] + matrix[0][1] * matrix[1][2] * matrix[2][0] +
                matrix[1][0] * matrix[0][2] * matrix[2][1] - matrix[0][2] * matrix[1][1] * matrix[2][0] -
                matrix[0][0] * matrix[2][1] * matrix[1][2] - matrix[1][0] * matrix[0][1] * matrix[2][2];
    }

    @Override
    public double getPolyhedronSurfaceArea(Polyhedron polyhedron) {
        Set<Point3D> points = polyhedron.getPoints();
        Point3D[] pointsArr = points.toArray(new Point3D[0]);

        double res = 0.0;
        res += getTriangle3DArea(pointsArr[0], pointsArr[1], pointsArr[2]);
        res += getTriangle3DArea(pointsArr[0], pointsArr[1], pointsArr[3]);
        res += getTriangle3DArea(pointsArr[0], pointsArr[2], pointsArr[3]);
        res += getTriangle3DArea(pointsArr[1], pointsArr[2], pointsArr[3]);

        return res;
    }

    private double getTriangle3DArea(Point3D pointA, Point3D pointB, Point3D pointC) {
        Point3D vectorAB = pointLogic.subtract(pointB, pointA);
        Point3D vectorAC = pointLogic.subtract(pointC, pointA);

        double angle = pointLogic.getAngle(vectorAB, vectorAC);
        double magnitudeAB = pointLogic.getMagnitude(vectorAB);
        double magnitudeAC = pointLogic.getMagnitude(vectorAC);

        return magnitudeAB * magnitudeAC * Math.sin(angle) / 2.0;
    }

    @Override
    public double getVolumesRatioDissectedByCoordinatePlane(Polyhedron polyhedron, CoordinatePlane coordinatePlane) {
        Set<Point3D> neededPoints;

        switch (coordinatePlane) {
            case OXY:
                neededPoints = findPointsNearCoordinatePlane(polyhedron, p -> p.getZ() > 0);
                break;
            case OYZ:
                neededPoints = findPointsNearCoordinatePlane(polyhedron, p -> p.getX() > 0);
                break;
            case OXZ:
                neededPoints = findPointsNearCoordinatePlane(polyhedron, p -> p.getY() > 0);
                break;
            default:
                throw new EnumConstantNotPresentException(CoordinatePlane.class, coordinatePlane.name());
        }

        Point3D[] pointsArr = neededPoints.toArray(new Point3D[0]);

        int size = pointsArr.length;
        if (size == 0 || size == 4) {
            return 0.0;
        }

        return onePointCase(polyhedron, pointsArr[0], coordinatePlane);
    }

    private double onePointCase(Polyhedron polyhedron, Point3D point, CoordinatePlane coordinatePlane) {
        Set<Point3D> points = polyhedron.getPoints();
        List<Point3D> res = new ArrayList<>(4);
        res.add(point);
        for (Point3D point3D : points) {
            if (!point3D.equals(point)) {
                Point3D v = pointLogic.subtract(point3D, point);
                res.add(getIntersectionPoint(point, v, coordinatePlane));
            }
        }

        Polyhedron part = new Tetrahedron(res);

        double partVolume = getPolyhedronVolume(part);
        return partVolume / (getPolyhedronVolume(polyhedron) - partVolume);
    }

    private Point3D getIntersectionPoint(Point3D point, Point3D vector, CoordinatePlane coordinatePlane) {
        double vZ = vector.getZ();
        double vY = vector.getY();
        double vX = vector.getX();

        double pZ = point.getZ();
        double pY = point.getY();
        double pX = point.getX();

        Double x = null, y = null, z = null, t = null;
        switch (coordinatePlane) {
            case OXY:
                if (vZ == 0) {
                    z = pZ;
                    break;
                }
                t = -pZ / vZ;
                break;
            case OYZ:
                if (vX == 0) {
                    x = pX;
                    break;
                }
                t = -pX / vX;
                break;
            case OXZ:
                if (vY == 0) {
                    y = pY;
                    break;
                }
                t = -pY / vY;
                break;
            default:
                throw new EnumConstantNotPresentException(CoordinatePlane.class, coordinatePlane.name());
        }

        if (t == null) {
            return new Point3D(x, y, z);
        } else {
            return new Point3D(
                    x == null ? pX + vX * t : x,
                    y == null ? pY + vY * t : y,
                    z == null ? pZ + vZ * t : z
            );
        }
    }

    private Set<Point3D> findPointsNearCoordinatePlane(
            Polyhedron polyhedron,
            Function<Point3D, Boolean> functionToFilterThePointsOutsidePlane) {
        Set<Point3D> points = polyhedron.getPoints();
        Set<Point3D> newPoints = new HashSet<>(points);

        Set<Point3D> savedPoints = new HashSet<>(4);
        for (Point3D point3D : points) {
            if (functionToFilterThePointsOutsidePlane.apply(point3D)) {
                savedPoints.add(point3D);
            }
        }

        int size = savedPoints.size();
        if (size == 3) {
            newPoints.removeAll(savedPoints);
            savedPoints = newPoints;
        }

        return savedPoints;
    }

    @Override
    public boolean isFaceOnCoordinatePlane(Polyhedron polyhedron, CoordinatePlane coordinatePlane) {
        Set<Point3D> points = polyhedron.getPoints();
        Point3D[] pointsArr = points.toArray(new Point3D[0]);

        return arePointOnAnyCoordinatePlane(pointsArr[0], pointsArr[1], pointsArr[2], coordinatePlane) ||
                arePointOnAnyCoordinatePlane(pointsArr[0], pointsArr[1], pointsArr[3], coordinatePlane) ||
                arePointOnAnyCoordinatePlane(pointsArr[0], pointsArr[2], pointsArr[3], coordinatePlane) ||
                arePointOnAnyCoordinatePlane(pointsArr[1], pointsArr[2], pointsArr[3], coordinatePlane);
    }

    private boolean arePointOnAnyCoordinatePlane(
            Point3D pointFirst, Point3D pointSecond, Point3D pointThird, CoordinatePlane coordinatePlane) {

        Point3D normalizedVectorByPlaneCoordinates
                = getNormalizedVectorByPlaneCoordinates(pointFirst, pointSecond, pointThird);

        double x = normalizedVectorByPlaneCoordinates.getX();
        double y = normalizedVectorByPlaneCoordinates.getY();
        double z = normalizedVectorByPlaneCoordinates.getZ();

        switch (coordinatePlane) {
            case OXY:
                return x == 0 && y == 0;
            case OYZ:
                return y == 0 && z == 0;
            case OXZ:
                return x == 0 && z == 0;
            default:
                throw new EnumConstantNotPresentException(CoordinatePlane.class, coordinatePlane.name());
        }
    }

    @Override
    public boolean isTetrahedron(Polyhedron polyhedron) {
        Set<Point3D> points = polyhedron.getPoints();
        Point3D[] pointsArr = points.toArray(new Point3D[0]);

        if (pointsArr.length != 4) {
            return false;
        }

        Point3D v1 = getNormalizedVectorByPlaneCoordinates(pointsArr[0], pointsArr[1], pointsArr[2]);
        Point3D v2 = getNormalizedVectorByPlaneCoordinates(pointsArr[0], pointsArr[1], pointsArr[3]);
        Point3D v3 = getNormalizedVectorByPlaneCoordinates(pointsArr[0], pointsArr[2], pointsArr[3]);
        Point3D v4 = getNormalizedVectorByPlaneCoordinates(pointsArr[1], pointsArr[2], pointsArr[3]);

        return isBasis(v1, v2, v3) &&
                isBasis(v1, v2, v4) &&
                isBasis(v1, v3, v4) &&
                isBasis(v2, v3, v4);
    }

    @Override
    public void sort(List<Polyhedron> polyhedrons, Comparator<Polyhedron> comparator) {
        polyhedrons.sort(comparator);
    }

    private boolean isBasis(Point3D vectorFirst, Point3D vectorSecond, Point3D vectorThird) {
        double[][] matrix = {
                {vectorFirst.getX(), vectorFirst.getY(), vectorFirst.getZ()},
                {vectorSecond.getX(), vectorSecond.getY(), vectorSecond.getZ()},
                {vectorThird.getX(), vectorThird.getY(), vectorThird.getZ()}
        };

        return calculate3x3Det(matrix) != 0.0;
    }

    private Point3D getNormalizedVectorByPlaneCoordinates(Point3D pointFirst, Point3D pointSecond, Point3D pointThird) {
        double dy1 = pointSecond.getX() - pointFirst.getX();
        double dz1 = pointThird.getX() - pointFirst.getX();

        double dy2 = pointSecond.getY() - pointFirst.getY();
        double dz2 = pointThird.getY() - pointFirst.getY();

        double dy3 = pointSecond.getZ() - pointFirst.getZ();
        double dz3 = pointThird.getZ() - pointFirst.getZ();

        return new Point3D(
                (dy3 * dz2 - dy2 * dz3),
                (dy1 * dz3 - dy3 * dz1),
                (dy2 * dz1 - dy1 * dz2)
        );
    }
}

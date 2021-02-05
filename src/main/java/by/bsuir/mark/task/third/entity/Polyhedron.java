package by.bsuir.mark.task.third.entity;

import java.util.*;

public abstract class Polyhedron {

    private Set<Point3D> points;

    public Polyhedron(Collection<Point3D> points) {
        Set<Point3D> pointsSet = new HashSet<>(points);
        this.points = Collections.unmodifiableSet(pointsSet);
    }

    public Polyhedron(Point3D... points) {
        Set<Point3D> pointsSet = new HashSet<>(Arrays.asList(points));
        this.points = Collections.unmodifiableSet(pointsSet);
    }

    public Set<Point3D> getPoints() {
        return points;
    }

    public void setPoints(Set<Point3D> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polyhedron polyhedron = (Polyhedron) o;
        return points == polyhedron.points
                || (points != null && points.equals(polyhedron.points));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (points == null ? 0 : points.hashCode());
        return result;
    }
}

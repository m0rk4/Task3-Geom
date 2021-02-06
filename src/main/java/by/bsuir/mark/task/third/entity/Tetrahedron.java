package by.bsuir.mark.task.third.entity;

import java.util.List;

public class Tetrahedron extends Polyhedron {
    public Tetrahedron(Point3D... points) {
        super(points);
    }

    public Tetrahedron(List<Point3D> points) {
        super(points);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Tetrahedron{points = " + getPoints() + "}";
    }
}

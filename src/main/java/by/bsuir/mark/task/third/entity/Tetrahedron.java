package by.bsuir.mark.task.third.entity;

import java.util.Collection;

public class Tetrahedron extends Polyhedron {

    private Integer id;

    public Tetrahedron(Point3D... points) {
        super(points);
    }

    public Tetrahedron(Collection<Point3D> points) {
        super(points);
    }

    public Tetrahedron(Integer id, Point3D... points) {
        this(points);
        this.id = id;
    }

    public Tetrahedron(Integer id, Collection<Point3D> points) {
        this(points);
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "Tetrahedron{id = " + id + ", points = " + getPoints() + "}";
    }
}

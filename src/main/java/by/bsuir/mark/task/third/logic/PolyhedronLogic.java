package by.bsuir.mark.task.third.logic;

import by.bsuir.mark.task.third.entity.CoordinatePlane;
import by.bsuir.mark.task.third.entity.Polyhedron;

import java.util.Comparator;
import java.util.List;

public interface PolyhedronLogic {
    double getPolyhedronVolume(Polyhedron polyhedron);
    double getPolyhedronSurfaceArea(Polyhedron polyhedron);
    double getVolumesRatioDissectedByCoordinatePlane(Polyhedron polyhedron, CoordinatePlane coordinatePlane);
    boolean isFaceOnCoordinatePlane(Polyhedron polyhedron, CoordinatePlane coordinatePlane);
    boolean isTetrahedron(Polyhedron polyhedron);
    void sort(List<Polyhedron> polyhedrons, Comparator<Polyhedron> comparator);
}

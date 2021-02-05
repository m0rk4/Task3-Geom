package by.bsuir.mark.task.third.logic;

import by.bsuir.mark.task.third.entity.CoordinatePlane;
import by.bsuir.mark.task.third.entity.Polyhedron;

public interface PolyhedronLogic {
    double getPolyhedronVolume(Polyhedron polyhedron);
    double getPolyhedronSurfaceArea(Polyhedron polyhedron);
    double getVolumesRatioDissectedByCoordinatePlane(Polyhedron polyhedron, CoordinatePlane coordinatePlane);
    boolean isFaceOnCoordinatePlane(Polyhedron polyhedron, CoordinatePlane coordinatePlane);
    boolean isTetrahedron(Polyhedron polyhedron);
}

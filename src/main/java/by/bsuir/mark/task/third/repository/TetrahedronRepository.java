package by.bsuir.mark.task.third.repository;

import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.specification.TetrahedronSpecification;

import java.util.List;

public interface TetrahedronRepository {
    Tetrahedron addTetrahedron(Tetrahedron tetrahedron);
    Tetrahedron updateTetrahedron(Tetrahedron tetrahedron);
    void removeTetrahedron(Tetrahedron tetrahedron);

    List<Tetrahedron> processQuery(TetrahedronSpecification specification);
}

package by.bsuir.mark.task.third.repository.impl;

import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.repository.IdGenerator;
import by.bsuir.mark.task.third.repository.TetrahedronRepository;
import by.bsuir.mark.task.third.specification.TetrahedronSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TetrahedronRepositoryImpl implements TetrahedronRepository {

    private final Map<Integer, Tetrahedron> tetrahedronSource;
    private final IdGenerator<Integer> idGenerator;

    public TetrahedronRepositoryImpl() {
        tetrahedronSource = new HashMap<>();
        idGenerator = new IntegerIdGeneratorImpl();
    }

    @Override
    public Tetrahedron addTetrahedron(Tetrahedron tetrahedron) {
        Tetrahedron tetrahedronToAdd = cloneTetrahedron(tetrahedron);
        Integer id = idGenerator.getNextId();

        tetrahedronToAdd.setId(id);
        tetrahedronSource.put(id, tetrahedronToAdd);
        return tetrahedronToAdd;
    }

    @Override
    public Tetrahedron updateTetrahedron(Tetrahedron tetrahedron) {
        Tetrahedron tetrahedronToUpdate = cloneTetrahedron(tetrahedron);
        Integer id = tetrahedron.getId();

        tetrahedronSource.replace(id, tetrahedronToUpdate);
        return tetrahedronToUpdate;
    }

    @Override
    public void removeTetrahedron(Tetrahedron tetrahedron) {
        Integer id = tetrahedron.getId();

        tetrahedronSource.remove(id);
    }

    @Override
    public List<Tetrahedron> processQuery(TetrahedronSpecification specification) {
        List<Tetrahedron> tetrahedrons = new ArrayList<>();
        for (Tetrahedron tetrahedron: tetrahedronSource.values()) {
            if (specification.isSpecified(tetrahedron)) {
                tetrahedrons.add(tetrahedron);
            }
        }
        return tetrahedrons;
    }

    private Tetrahedron cloneTetrahedron(Tetrahedron tetrahedron) {
        return new Tetrahedron(tetrahedron.getId(), tetrahedron.getPoints());
    }
}

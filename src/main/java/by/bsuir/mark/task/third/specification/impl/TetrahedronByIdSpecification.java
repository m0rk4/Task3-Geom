package by.bsuir.mark.task.third.specification.impl;

import by.bsuir.mark.task.third.entity.Tetrahedron;
import by.bsuir.mark.task.third.specification.TetrahedronSpecification;

public class TetrahedronByIdSpecification implements TetrahedronSpecification {
    private final Integer id;

    public TetrahedronByIdSpecification(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isSpecified(Tetrahedron tetrahedron) {
        return id.equals(tetrahedron.getId());
    }
}
